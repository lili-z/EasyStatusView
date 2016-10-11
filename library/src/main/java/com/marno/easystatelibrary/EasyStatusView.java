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
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Marno on 2016/10/10/13:26.
 * Function：多状态视图切换
 * Desc：
 */
public class EasyStatusView extends RelativeLayout {

    private static final int
            STATUS_CONTENT = 1,
            STATUS_LOADING = 2,
            STATUS_EMPTY = 3,
            STATUS_ERROR = 4,
            STATUS_NO_NET = 5;

    public static final int DEFAULT_LAYOUT = R.layout.esv_layout_default;

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

    private HashMap<Integer, View> mStatusViews = new HashMap<>();

    private ArrayList<View> mContentViews = new ArrayList();//保存内容控件

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
        inflater = LayoutInflater.from(mContext);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.EasyStatusView, defStyleAttr, 0);
        mEmptyLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_empty, DEFAULT_LAYOUT);
        mErrorLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_error, DEFAULT_LAYOUT);
        mLoadingLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_loading, DEFAULT_LAYOUT);
        mNoNetworkLayoutId = a.getResourceId(R.styleable.EasyStatusView_esv_noNet, DEFAULT_LAYOUT);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0, size = getChildCount(); i < size; i++) {
            mContentViews.add(getChildAt(i));
        }

        mEmptyView = inflater.inflate(mEmptyLayoutId, null);
        mLoadingView = inflater.inflate(mLoadingLayoutId, null);
        mErrorView = inflater.inflate(mErrorLayoutId, null);
        mNoNetworkView = inflater.inflate(mNoNetworkLayoutId, null);

        addViewToGroup(STATUS_EMPTY, mEmptyView);
        addViewToGroup(STATUS_LOADING, mLoadingView);
        addViewToGroup(STATUS_ERROR, mErrorView);
        addViewToGroup(STATUS_NO_NET, mNoNetworkView);

        setStatusViewsVisibility(GONE);
    }

    /**
     * 将不同状态下的视图添加到界面中，并保存在map中
     *
     * @param status 各状态视图对应的常量
     * @param view   各状态视图对应的布局
     */
    private void addViewToGroup(int status, View view) {
        mStatusViews.put(status, view);
        addView(view, 0, layoutParams);
    }

    /**
     * 改变视图状态
     *
     * @param viewStatus
     */
    private void changeViewStatus(int viewStatus) {
        if (viewStatus == STATUS_CONTENT) {
            setStatusViewsVisibility(STATUS_CONTENT);
            setContentViewVisibility(VISIBLE);
        } else {
            setContentViewVisibility(GONE);
            setStatusViewsVisibility(viewStatus);
            mStatusViews.get(viewStatus).setVisibility(VISIBLE);
        }
    }

    /**
     * 隐藏所有状态视图
     *
     * @param status 各状态对应的常量
     */
    private void setStatusViewsVisibility(int status) {
        Iterator<Map.Entry<Integer, View>> iterator = mStatusViews.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, View> entry = iterator.next();
            Integer key = entry.getKey();
            View view = entry.getValue();
            if (status > 1 && status != key) {
                view.setVisibility(GONE);
            } else {
                view.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置内容显示与隐藏
     *
     * @param visibility
     */
    private void setContentViewVisibility(int visibility) {
        if (mContentViews.size() > 0) {
            for (View view : mContentViews) {
                view.setVisibility(visibility);
            }
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
        changeViewStatus(STATUS_LOADING);
    }

    /**
     * 显示错误
     */
    public void error() {
        changeViewStatus(STATUS_ERROR);
    }

    /**
     * 显示没有网络
     */
    public void noNet() {
        changeViewStatus(STATUS_NO_NET);
    }

    /**
     * 显示空视图
     */
    public void empty() {
        changeViewStatus(STATUS_EMPTY);
    }

    /**
     * 设置空视图布局id
     *
     * @param emptyLayoutId
     */
    public void setEmptyLayoutId(int emptyLayoutId) {
        this.mEmptyLayoutId = emptyLayoutId;
        mEmptyView = inflater.inflate(emptyLayoutId, null);
        addViewToGroup(STATUS_EMPTY, mEmptyView);
    }

    /**
     * 设置错误视图布局id
     *
     * @param errorLayoutId
     */
    public void setErrorLayoutId(int errorLayoutId) {
        this.mErrorLayoutId = errorLayoutId;
        mErrorView = inflater.inflate(errorLayoutId, null);
        addViewToGroup(STATUS_ERROR, mErrorView);
    }

    /**
     * 设置加载中布局id
     *
     * @param loadingLayoutId
     */
    public void setLoadingLayoutId(int loadingLayoutId) {
        this.mLoadingLayoutId = loadingLayoutId;
        mLoadingView = inflater.inflate(loadingLayoutId, null);
        addViewToGroup(STATUS_LOADING, mLoadingView);
    }

    /**
     * 设置无网络布局id
     *
     * @param noNetworkLayoutId
     */
    public void setNoNetworkLayoutId(int noNetworkLayoutId) {
        this.mNoNetworkLayoutId = noNetworkLayoutId;
        mNoNetworkView = inflater.inflate(noNetworkLayoutId, null);
        addViewToGroup(STATUS_NO_NET, mNoNetworkView);
    }

    /**
     * 设置空视图view
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
        addViewToGroup(STATUS_EMPTY, mEmptyView);
    }

    /**
     * 设置加载错误视图view
     *
     * @param errorView
     */
    public void setErrorView(View errorView) {
        mErrorView = errorView;
        addViewToGroup(STATUS_ERROR, mErrorView);
    }

    /**
     * 设置加载中状态view
     *
     * @param loadingView
     */
    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
        addViewToGroup(STATUS_LOADING, mLoadingView);
    }

    /**
     * 设置无网络状态view
     *
     * @param noNetworkView
     */
    public void setNoNetworkView(View noNetworkView) {
        mNoNetworkView = noNetworkView;
        addViewToGroup(STATUS_NO_NET, mNoNetworkView);
    }

    /**
     * 获取空视图
     *
     * @return View
     */
    public View getEmptyView() {
        return mEmptyView;
    }

    /**
     * 获取错误视图
     *
     * @return View
     */
    public View getErrorView() {
        return mErrorView;
    }

    /**
     * 获取加载中视图
     *
     * @return View
     */
    public View getLoadingView() {
        return mLoadingView;
    }

    /**
     * 获取无网络视图
     *
     * @return View
     */
    public View getNoNetworkView() {
        return mNoNetworkView;
    }
}
