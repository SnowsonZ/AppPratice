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

#### Text Appearance
##### interface
1.  CharacterStyle
        MetricAffectingSpan
		    TextAppearanceSpan 设置text字体，颜色，大小，样式
			LocalSpan   text本地化
			AbsoluteSizeSpan  设置字体大小
			TypefaceSpan 更改字体
			ScaleXSpan X方向缩放
			ImageSpan 文字间添加图片
		SuggestionSpan 提供建议
2.  UpdateAppearance
        UpdateLayout
	        MetricAffectingSpan
		StrikethroughSpan   文字删除样式
		UnderlineSpan   下划线样式
		ClickableSpan  部分点击
		BackgroundColorSpan   背景颜色
		ForegroundColorSpan  前景颜色

##### Span
- 原意为跨度，在此表示text的部分内容