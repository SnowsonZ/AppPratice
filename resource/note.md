#### ScrollView嵌套ListView或者GridView导致显示不全
```
ScrollView

final int childHeightMeasureSpec = MeasureSpec.makeSafeMeasureSpec(
                MeasureSpec.getSize(parentHeightMeasureSpec), MeasureSpec.UNSPECIFIED);

ListView or GridView

if (mItemCount > 0 && (widthMode == MeasureSpec.UNSPECIFIED
                || heightMode == MeasureSpec.UNSPECIFIED)) {
    //省略部分code
    final View child = obtainView(0, mIsScrap);
    childHeight = child.getMeasuredHeight();
}
//heightSize == 单个item的高度
if (heightMode == MeasureSpec.UNSPECIFIED) {
    heightSize = mListPadding.top + mListPadding.bottom + childHeight +
   getVerticalFadingEdgeLength() * 2;
}
```

#### Gson
##### 基本数据类型的解析与生成
```
解析
Gson gson = new Gson();
int i = gson.fromJson("100", int.class);              //100
double d = gson.fromJson("\"99.99\"", double.class);  //99.99
boolean b = gson.fromJson("true", boolean.class);     // true
String str = gson.fromJson("String", String.class);   // String

生成
Gson gson = new Gson();
String jsonNumber = gson.toJson(100);       // 100
String jsonBoolean = gson.toJson(false);    // false
String jsonString = gson.toJson("String"); //"String"
```

##### 容错
> 主要是解析和生成POJO类时，字段名称与预期不符的问题
- @SerializedName(value="fieldName0", alternate={"fieldName1", "fieldName2"})

##### 泛型
- TypeToken
```
使用原因：泛型擦除
```

##### 流式序列化和反序列化
```
从流中反序列化以及序列化到流
```

##### GsonBuilder
```
修改Gson的默认行为
```

##### 字段过滤
1. @Expose(serialize=true, deserialize=true),配合GsonBuilder
```
public class Category {
    @Expose public int id;
    @Expose public String name;
    @Expose public List<Category> children;
    //不需要序列化,所以不加 @Expose 注解，
    //等价于 @Expose(deserialize = false,serialize = false)
    public Category parent; 
}
Gson gson = new GsonBuilder()
          .excludeFieldWithoutExposeAnnotation()
		  .create();
gson.toJson();
```

2. 基于版本
- @Since(version) @Until(version), 配合GsonBuilder使用
```
规则: 当前版本>= Since版本 || <5,则序列化,否则，不序列化
```

3. 基于修饰符
```
public static final protected private 
new GsonBuilder().excludeFieldWithModifiers(Modifier.FINAL, Modifier.STATIC).create()
```

4. 基于策略（自定义规则）
- 使用ExclusionStrategy接口
```
Gson gson = new GsonBuilder()
        .addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                // 这里作判断，决定要不要排除该字段,return true为排除
                if ("finalField".equals(f.getName())) return true; //按字段名排除
                Expose expose = f.getAnnotation(Expose.class); 
                if (expose != null && expose.deserialize() == false) return true; //按注解排除
                return false;
            }
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                // 直接排除某个类 ，return true为排除
                return (clazz == int.class || clazz == Integer.class);
            }
        })
        .create();
```
##### POJO与json映射规律
- 即自定义@SerializedName
```
GsonBuilder提供了FieldNamingStrategy接口和setFieldNamingPolicy和setFieldNamingStrategy 两个方法。
Gson默认实现使用setFieldNamingPolicy和FieldNamingPolicy枚举类。
Gson gson = new GsonBuilder()
        .setFieldNamingStrategy(new FieldNamingStrategy() {
            @Override
            public String translateName(Field f) {
                //实现自己的规则
                return null;
            }
        })
        .create();
*** @SerializedName注解拥有最高优先级，在加有@SerializedName注解的字段上FieldNamingStrategy不生效！
```

##### TypeAdapter
- 接管某种类型的序列化及反序列化
```
public class UserTypeAdapter extends TypeAdapter<User> {

    @Override
    public void write(JsonWriter out, User value) throws IOException {
        out.beginObject();
        out.name("name").value(value.name);
        out.name("age").value(value.age);
        out.name("email").value(value.email);
        out.endObject();
    }

    @Override
    public User read(JsonReader in) throws IOException {
        User user = new User();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "name":
                    user.name = in.nextString();
                    break;
                case "age":
                    user.age = in.nextInt();
                    break;
                case "email":
                case "email_address":
                case "emailAddress":
                    user.email = in.nextString();
                    break;
            }
        }
        in.endObject();
        return user;
    }
}

User user = new User("怪盗kidou", 24);
user.emailAddress = "ikidou@example.com";
Gson gson = new GsonBuilder()
        //为User注册TypeAdapter
        .registerTypeAdapter(User.class, new UserTypeAdapter())
        .create();
System.out.println(gson.toJson(user));

***优先级较高，之前的@Since @Until @Expose 都会失效
```
- registerTypeHierarchyAdapter 和registerTypeAdapter
```
前者支持继承但不支持泛型，后者支持泛型但不支持继承
```

##### TypeAdapterFactory
- 工厂类，用于查询被接管的类
```
Gson gson = new GsonBuilder()
    .registerTypeAdapterFactory(new TypeAdapterFactory() {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return null;
        }
    })
    .create();
```

##### @JsonAdapter注解
- 注解在类上，不在需要注册，直接使用Gson即可。
```
@JsonAdapter(UserTypeAdapter.class) //加在类上
public class User {
    public User() {
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public String name;
    public int age;
    @SerializedName(value = "emailAddress")
    public String email;
}

注： @JsonAdapter 仅支持 TypeAdapter或TypeAdapterFactory( 2.7开始已经支持 JsonSerializer/JsonDeserializer)
     JsonAdapter的优先级比GsonBuilder.registerTypeAdapter的优先级更高。
```

##### 解决字段类型变化的问题
```
问题： 正确返回时为list，错误时为字符串
方案一：
Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {
    @Override
    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray()){
            //这里要自己负责解析了
            Gson newGson = new Gson();
            return newGson.fromJson(json,typeOfT);
        }else {
            //和接口类型不符，返回空List
            return Collections.EMPTY_LIST;
        }
    }
}).create();

方案二：
Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {
    @Override
    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray()) {
            JsonArray array = json.getAsJsonArray();
            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            List list = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JsonElement element = array.get(i);
                Object item = context.deserialize(element, itemType);
                list.add(item);
            }
            return list;
        } else {
            //和接口类型不符，返回空List
            return Collections.EMPTY_LIST;
        }
    }
}).create();
```

#### Java访问修饰符
1. public 公开级访问权限
2. default 包级访问权限
3. protected 子类+private及访问权限
4. private 类级访问权限