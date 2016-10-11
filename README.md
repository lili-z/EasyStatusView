#RapidLibs
--------------------------
####一、简介：
一个多状态视图切换的控件，继承自RelativeLayout可以直接作为最外层布局使用，支持在布局中或代码中指定不同状态下的布局

**1.1 Gradle集成**

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

```
dependencies {
     compile 'com.github.MarnonDev:EasyStatusView:v1.0.0'
}
```

####二、截图预览
![](https://github.com/MarnonDev/EasyStatusView/blob/master/screenshot/EasyStatusView.gif)

####三、使用方法
**3.1 属性列表**
>|属性名称|说明|
>|:---:|:---:|
>|esv_loading|指定加载中布局|
>|esv_empty|指定空视图|
>|esv_error|指定错误视图|
>|esv_noNet|指定无网络视图|

**3.2 提供方法**
>|方法名|作用|
>|:---:|:---:|
>|empty()|显示空视图|
>|error()|显示加载错误|
>|loading()|显示加载中|
>|noNet()|显示无网络连接|
>|content()|显示内容|
>|setEmptyView()|设置空布局|
>|setErrorView()|设置加载错误布局|
>|setLoadingView()|设置加载中布局|
>|setNoNetworkView()|设置无网络布局|
>|setEmptyLayoutId()|设置空布局资源id|
>|setErrorLayoutId()|设置错误布局资源id|
>|setLoadingLayoutId()|设置加载中布局资源id|
>|setNoNetworkLayoutId()|设置无网络布局资源id|
>|getEmptyView()|获取空布局|
>|getErrorView()|获取错误布局|
>|getLoadingView()|获取加载中布局|
>|getNoNetworkView()|获取无网络布局|

**3.3 示例代码**

在布局资源文件中中指定对应视图布局资源id
```
<com.marno.easystatelibrary.EasyStatusView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/esvlayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:esv_empty="@layout/layout_empty"
    app:esv_error="@layout/layout_error"
    app:esv_loading="@layout/layout_loading"
    app:esv_noNet="@layout/layout_no_net">

    <ListView
        tools:listitem="@layout/item_main"
        android:id="@+id/lv_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</com.marno.easystatelibrary.EasyStatusView>
```

```
EasyStatusView mEsvLayout = (EasyStatusView) findViewById(R.id.esvlayout);
mEsvLayout.empty();//显示空视图
mEsvLayout.error();//显示错误视图
mEsvLayout.loading();//显示加载中视图
mEsvLayout.noNet();//显示无网络视图
mEsvLayout.content();//显示内容
```

或者在代码中设定对应状态布局资源id

```
<com.marno.easystatelibrary.EasyStatusView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/esvlayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        tools:listitem="@layout/item_main"
        android:id="@+id/lv_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</com.marno.easystatelibrary.EasyStatusView>
```

```
EasyStatusView mEsvLayout = (EasyStatusView) findViewById(R.id.esvlayout);
        
//没有在布局属性中指定对应状态的布局时，需要在代码中先指定好才能使用
mEsvLayout.setEmptyLayoutId(R.layout.layout_empty);
mEsvLayout.setErrorLayoutId(R.layout.layout_error);
mEsvLayout.setLoadingLayoutId(R.layout.layout_loading);
mEsvLayout.setNoNetworkLayoutId(R.layout.layout_no_net);

mEsvLayout.empty();//显示空视图
mEsvLayout.error();//显示错误视图
mEsvLayout.loading();//显示加载中视图
mEsvLayout.noNet();//显示无网络视图
mEsvLayout.content();//显示内容
```

####四、关于我 Marno

- 邮箱：marnodev@163.com
- 简书：[点击关注Marno的简书](http://www.jianshu.com/users/174a09ba6c25)

