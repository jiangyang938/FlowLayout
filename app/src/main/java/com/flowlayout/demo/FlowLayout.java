package com.flowlayout.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/8/1.
 */
public class FlowLayout extends ViewGroup {

/*自定义ViewGroup支持子控件的layout_margin参数，则自定义的ViewGroup类必须重载generateLayoutParams()函数，并且在该函数中返回一个ViewGroup.MarginLayoutParams派生类对象，这样才能使用margin参数。*/
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

    public LayoutParams(int width, int height) {
        super(width, height);
    }
}
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FlowLayout.LayoutParams(getContext(), attrs);
    }


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width=right-left;
        int childLeft=0;
        int childTop=0;
        int maxHeight=0;
        final int childCount=getChildCount();
        for(int i=0;i<childCount;i++){
            final View childView=getChildAt(i);
            if(childView.getVisibility()!= View.GONE){
                int childWidth=childView.getMeasuredWidth();
                int childHeight=childView.getMeasuredHeight();

                FlowLayout.LayoutParams lp=(FlowLayout.LayoutParams) childView.getLayoutParams();

                if((childLeft+childWidth+lp.leftMargin+lp.rightMargin)>width){
                    childLeft=0;
                    childTop+=maxHeight;
                    maxHeight=childHeight+lp.topMargin+lp.bottomMargin;
                }else{
                    if((childHeight+lp.topMargin+lp.bottomMargin)>maxHeight){
                        maxHeight=childHeight+lp.topMargin+lp.bottomMargin;
                    }
                }
                childView.layout(childLeft+lp.leftMargin,childTop+lp.topMargin,childLeft+lp.leftMargin+childWidth,childTop+lp.topMargin+childHeight);
                childLeft+=childWidth+lp.leftMargin+lp.rightMargin;
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth=0;
        int sreenWidth=0;
        int measureHeight=0;
        int maxHeigth=0;
        final int childCount=getChildCount();
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int widthSpaceSize= MeasureSpec.getSize(widthMeasureSpec);
        int widthSpaceMoth= MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize= MeasureSpec.getSize(heightMeasureSpec);
        int heightSpaceMoth= MeasureSpec.getMode(heightMeasureSpec);
        if(childCount==0){
            setMeasuredDimension(0,0);
        }else{
            if(widthSpaceMoth== MeasureSpec.AT_MOST&&heightSpaceMoth== MeasureSpec.AT_MOST){
                for(int i=0;i<childCount;i++){
                    View childView=getChildAt(i);
                    if(childView.getVisibility()!= View.GONE){
                        final int childWidth=childView.getMeasuredWidth();
                        final int childHeight=childView.getMeasuredHeight();

                        final FlowLayout.LayoutParams lp=(FlowLayout.LayoutParams) childView.getLayoutParams();

                        if((sreenWidth+childWidth+lp.leftMargin+lp.rightMargin)>widthSpaceSize){
                            measureWidth=widthSpaceSize;
                            sreenWidth=childWidth+lp.leftMargin+lp.rightMargin;
                            maxHeigth=childHeight+lp.topMargin+lp.bottomMargin;
                            measureHeight+=maxHeigth;


                        }else{
                            measureWidth+=childWidth+lp.leftMargin+lp.rightMargin;
                            sreenWidth+=childWidth+lp.leftMargin+lp.rightMargin;
                            if((childHeight+lp.topMargin+lp.bottomMargin)>maxHeigth){
                                measureHeight=measureHeight-maxHeigth+childHeight+lp.topMargin+lp.bottomMargin;
                                maxHeigth=childHeight+lp.topMargin+lp.bottomMargin;

                            }
                        }
                    }
                }
                if(measureWidth>widthSpaceSize){
                    measureWidth=widthSpaceSize;
                }
                setMeasuredDimension(measureWidth,measureHeight);
            }else if(heightSpaceMoth== MeasureSpec.AT_MOST){
                for(int i=0;i<childCount;i++){
                    View childView=getChildAt(i);
                    if(childView.getVisibility()!= View.GONE){
                        final int childWidth=childView.getMeasuredWidth();
                        final int childHeight=childView.getMeasuredHeight();
                        //出错点
                        final FlowLayout.LayoutParams lp=(FlowLayout.LayoutParams) childView.getLayoutParams();

                        if((sreenWidth+childWidth+lp.leftMargin+lp.rightMargin)>widthSpaceSize){
                            measureWidth=widthSpaceSize;
                            sreenWidth=childWidth+lp.leftMargin+lp.rightMargin;
                            maxHeigth=childHeight+lp.topMargin+lp.bottomMargin;
                            measureHeight+=maxHeigth;
                        }else{
                            measureWidth+=childWidth+lp.leftMargin+lp.rightMargin;
                            sreenWidth+=childWidth+lp.leftMargin+lp.rightMargin;
                            if((childHeight+lp.topMargin+lp.bottomMargin)>maxHeigth){
                                measureHeight=measureHeight-maxHeigth+childHeight+lp.topMargin+lp.bottomMargin;
                                maxHeigth=childHeight+lp.topMargin+lp.bottomMargin;
                            }
                        }
                    }
                }
                setMeasuredDimension(widthSpaceSize,measureHeight);
            }else if(widthSpaceMoth== MeasureSpec.AT_MOST){
                for(int i=0;i<childCount;i++){
                    View childView=getChildAt(i);
                    if(childView.getVisibility()!= View.GONE){
                        int childWidth=childView.getMeasuredWidth();
                        if((measureWidth+childWidth)>widthSpaceSize){
                            measureWidth=widthSpaceSize;
                        }else{
                            measureWidth+=childWidth;
                        }
                    }
                }
            }
        }

    }
}
