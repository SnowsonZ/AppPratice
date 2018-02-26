* ScrollView嵌套ListView或者GridView导致显示不全
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