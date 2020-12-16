# Lobster
    一个用于Android 应用组件化时各组件的Application进行解耦的轻便型框架。 三个注解即可搞定！


### 一、功能介绍
    1.在组件中不使用BaseApplication实例，通过注解，直接使用组件自己创建的Application实例；
    2.组件中自己创建的Application生命周期方法伴随壳子工程Application生命周期调用而调用；
    3.组件中自己创建的Application可以配置优先级，用于优先或延后执行。

### 二、应用场景
    组件化框架中，各组件有时需要持有Application的实例，但很多做法是在公共库中创建BaseApplication，  
    让壳子工程的Application去继承BaseApplication，进而组件去持有BaseApplication的实例达到使用的目的，  
    然而这样会加剧组件对公共库的过分依赖，项目较大时，就会造成一定的耦合，可能会出现改一处而动全身的场景。  
    因此，在组件化当中，各组件应该像一个应用一样维护一个自己的Application，使用时拿的是自己Application的实例，  
    与其他组件隔离，也与公共库隔离，降低耦合！

### 三、使用方式
1.需要在壳子工程和其他module中添加如下依赖：
    
    android {
        defaultConfig {
        ...
            javaCompileOptions {
                annotationProcessorOptions {
                    arguments = [LOBSTER_MODULE_NAME: project.getName()]
                }
            }
        }
    }
    
    dependencies {
        implementation project(path: ':lobster-annotation')
        annotationProcessor project(path: ':lobster-compiler')
        ...
    }
    
2.在壳子工程和其他Module中的Application中添加注解：
ShellApp注解作用于壳子工程（主工程）Application，一般来说只有一个，ModuleApp注解作用于组件Application，可以设置优先级，AppInstance注解作用于组件Application的实例。
    
    // 壳子工程的Application
    @ShellApp
    public class MyApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
        }
    }
    // 其他Module的Application
    @ModuleApp(priority = 1)
    public class Module1App extends Application {
        private static final String TAG = "Lobster";

        @AppInstance
        public static Application mApplication1;

        @Override
        public void onCreate() {
            super.onCreate();
            Log.i(TAG , "Module1App->onCreate");
        }
    }
    // 其他Module的Application
    @ModuleApp(priority = 2)
    public class Module2App extends Application {
        private static final String TAG = "Lobster";
        @AppInstance
        public static Application mApplication2;

        @Override
        public void onCreate() {
            super.onCreate();
            Log.i(TAG , "Module2App->onCreate");
            Toast.makeText(mApplication2, "I come from Module2App", Toast.LENGTH_SHORT).show();
        }
    }
    

3.没有了，可以开始耍了！
