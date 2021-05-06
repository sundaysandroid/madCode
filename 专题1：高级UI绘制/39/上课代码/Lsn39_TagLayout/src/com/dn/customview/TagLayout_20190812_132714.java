package com.dn.customview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class TagLayout extends ViewGroup {
	//��¼ÿһ���ж��
	List<Integer> lineHeights = new ArrayList<Integer>();
	List<List<View>> views = new ArrayList<List<View>>();
	
	public TagLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		views.clear();
		lineHeights.clear();
		//1.����
		//�����ж���������
		List<View> lineViews = new ArrayList<>();
		int width = getMeasuredWidth();//�����Լ��Ŀ��
		int lineWidth = 0;
		int lineHeight = 0;//��һ�е����߶�
		int childCount = getChildCount();
		for (int j = 0; j < childCount; j++) {
			View child = getChildAt(j);
			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			int childWidth = child.getMeasuredWidth();
			int childHeigh = child.getMeasuredHeight();
			if(childWidth + lp.leftMargin + lp.rightMargin+lineWidth>width){
				//����,����
				lineHeights.add(lineHeight);
				views.add(lineViews);
				lineWidth = 0;
				lineViews = new ArrayList<View>();
			}
			lineWidth += childWidth + lp.leftMargin +lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeigh + lp.topMargin + lp.bottomMargin);
			lineViews.add(child);
		}
		lineHeights.add(lineHeight);
		views.add(lineViews);
		int left = 0;
		int top = 0;
		//2.�ڷ�
		int size = views.size();
		for (int i = 0; i < size; i++) {
			lineViews = views.get(i);
			lineHeight = lineHeights.get(i);
			for (int j = 0; j < lineViews.size(); j++) {
				//������һ�е�����child
				View child = lineViews.get(j);
				MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
				int lc = left + lp.leftMargin;
				int tc = top + lp.topMargin;
				int rc = lc + child.getMeasuredWidth();
				int bc = tc + child.getMeasuredHeight();
				child.layout(lc, tc, rc, bc);
				left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			}
			left = 0;
			top += lineHeight;
		}
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		int width = 0;//width=��������������һ��
		int height = 0;//height=�����еĸ߶����
		//һ�еĿ��=һ�е��е�����view�Ŀ�ȵĺ�
		int lineWidth = 0;
		int lineHeight = 0;
		
		//1.���������ӿؼ��Ĵ�С
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			//�ӿؼ���ʵռ�õĿ�͸߶�
			int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
			//��һ�зŲ��µ�ʱ����Ҫ����
			if(lineWidth + childWidth>sizeWidth){
				//����
				width = Math.max(lineWidth, width);
				lineWidth = childWidth;
				
				height += lineHeight;
				lineHeight = childHeight;
			}else{//�ۼ�
				lineWidth +=childWidth;
				lineHeight = Math.max(lineHeight, childHeight);
			}
			//���һ��
			if(i==childCount-1){
				width = Math.max(width, lineWidth);
				height += lineHeight;
			}
		}
		
		//2.��������������Ĵ�С
		int measuredWidth = (modeWidth ==  MeasureSpec.EXACTLY)?sizeWidth:width;//wrap_content/match_parent/EXACTLY
		int measuredHeight = (modeHeight ==  MeasureSpec.EXACTLY)?sizeHeight:height;//wrap_content/match_parent/EXACTLY
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	@Override
	protected LayoutParams generateLayoutParams(LayoutParams p) {
		// TODO Auto-generated method stub
		return new MarginLayoutParams(p);
	}
	
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new MarginLayoutParams(getContext(), attrs);
	}
	
}
