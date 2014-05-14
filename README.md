CommonlyUsedUtils
=================


These are some of the Utils that I find myself writing every single time I write a new application.

This is an android studio Android Library Module


## I believe all methods are listed.
I had a little time on the flight so I updated the readme I believe i got all of the methods listed please let me know if i left anything out.


Contructor:
```Java
public CommonlyUsedUtils(Context cxt) {
	mContext = cxt;
	mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
	editor = mPrefs.edit();
	try {
		fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
	} catch (ClassCastException e) {
		e.printStackTrace();
	}
}
```

How to Use:

```Java
CommonlyUsedUtils utils = new CommonlyUsedUtils(MainActivity.this);
utils.getFavorites();
```

Methods:
```java
//SharedPreferences 

public void setIntPref(int number)
public int getIntPref()

public void setStringPref(String string)
public String getStringPref() 

public void setBooleanPref(boolean mbool)
public boolean getBooleanPref()

public void setCustomObjectPref(Object obj)
public Object getCustomObjectPref()


public void clearSharedPrefs()

//ScreenDimensions
public static Point getWindowDisplaySize(WindowManager wm)


//Different Intents

public void goToActivityWithStyle(Class<?> clazz, Bundle bundle,String bundleKey) {

public void callAPhone(String phoneNumber)
public void GetDirectionsTo(String Address)
public void GoToWebsite(String websiteURL)
public void CreateEmail(String[] recipients, String Title, String Text)
public void PlayYouTubeVideo(String YouTubeUrlVideoId)

// Fragments
public void switchFragmentsWithStyle(Fragment frag)
public void switchFragmentsWithStyle(Fragment frag, String backStackString)

//Keyboard Utils

public void showKeyboard(Window window)
public void showSoftKeyboard(View view)
public void hideSoftKeyboard(View view)

//Dialog Utils

public AlertDialog AlertMessage(int Drawable, String Message)
public AlertDialog AlertMessage(int Drawable, int Message)
public AlertDialog AlertMessage(int Drawable, String Title, String Message)
public AlertDialog AlertMessage(int Drawable, int Title, int Message) 

//Date Formatting

public String longDateStringStandardTime(Date date)
public String longDateStringMilitaryTime(Date date)

//Misc. Utils
public void refreshArrayAdapterWithNewObjects(ArrayAdapter aa, ArrayList objs, Boolean clearWhatisInAdapter)
public void pressBackButtonTwiceToExit()
////////////////////////////////////////////////////////////////
//Usage of utils.pressBackButtonTwiceToExit();
@Override
    public void onBackPressed() {
        utils.pressBackButtonTwiceToExit();
    }
////////////////////////////////////////////////////////////////
public static boolean isTablet(Context context)
public static boolean isOnline(Context context)
public String BitmapToBase64(Bitmap bm, Bitmap.CompressFormat format)
public static boolean deleteNon_EmptyDir(File dir)

CommonlyUsedUtilsBeta.java Note: not completely tested still has bugs.
public void revealPassWord(final EditText et, final MotionEvent event) 

```

Static Methods:
```java 
ActionBarUtils.java
public static AutoCompleteTextView GetSupportV7ActionBarSeachEditText(MenuItem item)
public static EditText GetActionBarSeachEditText(MenuItem item, int SearchViewId)

CommonlyUsedUtils.java
public static Point getWindowDisplaySize(WindowManager wm)
public static void goToActivityWithStyle(Context mContext, Class<?> clazz, Bundle bundle, String bundleKey) 


CommonlyUsedUtilsBeta.java Note: not completely tested still has bugs.
public static Bitmap createACircularImage(Bitmap bm)

DateTimeUtils.java Note: uses JodaTime to complete.
public static String GetTimeInBetween(DateTime start, DateTime end)
public static DateTime StringToDateTime(String date, String dateFormat)
public static void SplashTimer(Activivty act, int inMilliSecs, Class whereTo)


```
##CustomViews:
```text

setup for endless scrolling.
EndlessScrollingAdapter.java
a fragment adpater that takes the lifecycles into consideration.
SmartFragmentStatePagerAdapter.java

Allows for Custom font to be added effortlessly.
KustomTextView.java
```

##LICENSE
----------------------------------------------------------------------------

Copyright 2013 Aaron Fleshner

"THE BEER-WARE LICENSE" (Revision 42):
<phk@FreeBSD.ORG> wrote this file. As long as you retain this notice you can do whatever you want with this stuff. If we meet some day, and you think this stuff is worth it, you can buy me a beer in return Poul-Henning Kamp

----------------------------------------------------------------------------
