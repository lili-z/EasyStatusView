package com.marno.easystatelibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marno on 2016/10/10/13:26.
 * Function：多状态视图切换
 * Desc：
 */
public class EasyStatusView extends RelativeLayout {

    private static final int
            STATUS_CONTENT = 0X001,
            STATUS_LOADING = 0X002,
            STATUS_EMPTY = 0X003,
            STATUS_ERROR = 0X004,
            STATUS_NO_NET = 0X005,
            DEFAULT_VALUE = -1;

    private View
            mEmptyView,//空视图
            mErrorView,  //错误视图
            mLoadingView,//加载中视图
            mNoNetworkView; //无网络视图

    private int
            mEmptyLayoutId,//空视图布局id
            mErrorLayoutId,//错误视图布局id
            mLoadingLayoutId,  //加载中视图布局id
            mNoNetworkLayoutId; //无网络视图布局id

    //视图当前状态
    private int mViewStatus;

    private HashMap<Integer, View> mStatusViews = new HashMap<>();
    //保存内容控件
    private ArrayList<View> mContentViews = new ArrayList();

    private LayoutInflater inflater;
    private Context mContext;

    private final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public EasyStatusView(Context context) {
        this(context, null);
    }

    public EasyStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EasyStatusView,
                defStyleAttr, 0);
        mEmptyLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_empty, DEFAULT_VALUE);
        mErrorLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_error, DEFAULT_VALUE);
        mLoadingLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_loading, DEFAULT_VALUE);
        mNoNetworkLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_noNet, DEFAULT_VALUE);
        a.recycle();

        for (View view : mContentViews) {
            view.setVisibility(VISIBLE);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflater = LayoutInflater.from(mContext);

        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            mContentViews.add(getChildAt(i));
        }

        mEmptyView = inflater.inflate(mEmptyLayoutId, null);
        mErrorView = inflater.inflate(mErrorLayoutId, null);
        mLoadingView = inflater.inflate(mLoadingLayoutId, null);
        mNoNetworkView = inflater.inflate(mNoNetworkLayoutId, null);
        mStatusViews.put(STATUS_LOADING, mLoadingView);
        mStatusViews.put(STATUS_ERROR, mErrorView);
        mStatusViews.put(STATUS_EMPTY, mEmptyView);
        mStatusViews.put(STATUS_NO_NET, mNoNetworkView);
        content();
    }

    private void changeViewStatus(int viewStatus) {
        if (viewStatus == STATUS_CONTENT) {

        } else {
            View view = mStatusViews.get(viewStatus);
            addView(view, 0, layoutParams);
        }
    }

    /**
     * 显示内容
     */
    public void content() {
        changeViewStatus(STATUS_CONTENT);
    }

    /**
     * 显示加载中
     */
    public void loading() {

    }

    /**
     * 显示错误
     */
    public void error() {

    }

    /**
     * 显示没有网络
     */
    public void noNet() {

    }

    /**
     * 显示空视图
     */
    public void empty() {

    }

    /**
     * 设置空视图布局id
     *
     * @param emptyLayoutId
     */
    public void setEmptyLayoutId(int emptyLayoutId) {
        this.mEmptyLayoutId = emptyLayoutId;
    }

    /**
     * 设置错误视图布局id
     *
     * @param errorLayoutId
     */
    public void setErrorLayoutId(int errorLayoutId) {
        this.mErrorLayoutId = errorLayoutId;
    }

    /**
     * 设置加载中布局id
     *
     * @param loadingLayoutId
     */
    public void setLoadingLayoutId(int loadingLayoutId) {
        this.mLoadingLayoutId = loadingLayoutId;
    }

    /**
     * 设置无网络布局id
     *
     * @param noNetworkLayoutId
     */
    public void setNoNetworkLayoutId(int noNetworkLayoutId) {
        this.mNoNetworkLayoutId = noNetworkLayoutId;
    }
}
