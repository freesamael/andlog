AndLog
======

Android Log utility (http://developer.android.com/reference/android/util/Log.html) is easy and fast. However it's annoying to have almost all logs being inside ```if (isLoggable(TAG, Log.DEBUG)) { ... }``` block. Developers who want to visualize activity lifecycle and what Intent causes activity being launched might offen end up with a class definition like this:

```java
public class BrowserActivity extends ActionBarActivity {
    private static final String TAG = "BrowserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "onCreate: intent=" + getIntent());
        }

        setContentView(R.layout.activity_browser);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "onStart");
        }
    }

...

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "onDestroy");
        }
    }
}
```

Not only it make you feeling annoying to use ```isLoggable``` everywhere, but it also doesn't generate really useful log records. In this example, only the schema of data URI is logged, developers have to do more to log the full path of data URI.

```
D/BrowserActivity(21833): intent=Intent { act=android.intent.action.VIEW dat=http: flg=0x13400000 cmp=org.andlog.sample/.BrowserActivity }
```

In contrast, when using AndLog, the class can be simplified:

```java
public class BrowserActivity extends ActionBarActivity {
    private Logger mLogger = new VerboseLoggerFactory(this).create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger.debug("intent=", getIntent());
        setContentView(R.layout.activity_browser);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLogger.debug();
    }

...

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLogger.debug();
    }
}
```

Further, it generates more meaningful log records. In this example, it shows the full data URI; In other cases, developers will find the benifits of the ability to parse whole ```Bundle``` automatically.

```
D/BrowserActivity(22572): onCreate(): intent=Intent {act=android.intent.action.VIEW dat=http://www.google.com flg=0x13400000 cmp=org.andlog.sample/.BrowserActivity}
```

It's noticible that in the above example, parameters are not concated using a plus symbol, but seperated by a comma. AndLog uses ```StringBuilder``` internally for better performance and avoid unnecesary ```+``` evaluation.

There are 2 built-in Logger factories - ```SimpleLoggerFactory``` and ```VerboseLoggerFactory```. While the above example has shown ```VerboseLoggerFactory``` generates much debug message content, ```SimpleLoggerFactory```, on the other hands, generates minimal log message content for best performance. Beside the built-in factories, developers are free to use their own factory by implementing the inteface ```LoggerFactory``` as well.