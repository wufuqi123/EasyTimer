```
    一个简单的计时器。
```

#### 基础功能


1. 添加依赖

    请在 build.gradle 下添加依赖。

    ``` 
        implementation 'cn.wufuqi:EasyTimer:1.0.2'
    ```
2. 设置jdk8或更高版本

    因为本sdk使用了jdk8才能使用的 Lambda 表达式，所以要在 build.gradle 下面配置jdk8或以上版本。

    ``` 
    android {
        ....

        compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
        
    }
    ```

3. 创建一个计时器实例对象

    ```
        val easyTimer = EasyTimer()
    ```

4. 一个其他线程内一直回调的定时器

    ```
        val uuid = easyTimer.schedule(500){
            // 这里面 0.5秒执行一次,一直执行
            // 不是主线程执行
        }
    ```

6. 一个其他线程执行一次的定时器

    ```
        val uuid = easyTimer.scheduleOne(500){
            // 这里面 0.5秒执行一次，只执行一次
            // 不是主线程执行
        }
    ```

7. 主线程一直回调的定时器

    ```
        val uuid = easyTimer.scheduleUI(500){
            // 这里面 0.5秒执行一次,一直执行
            // 主线程执行
        }
    ```

8. 主线程执行一次的定时器

    ```
        val uuid = easyTimer.scheduleOneUI(500){
            // 这里面 0.5秒执行一次，只执行一次
            // 不是主线程执行
        }
    ```

9. 取消一个定时器

    ```
        easyTimer.unschedule(uuid)
    ```

10. 取消全部定时器

    ```
        easyTimer.unscheduleAll()
    ```