CommonlyUsedUtils
=================

These are some of the Utils that I find myself writing every single time I write a new application.

This is an android studio Android Library Module


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
public static boolean isTablet(Context context)
public static boolean isOnline(Context context)
public String BitmapToBase64(Bitmap bm, Bitmap.CompressFormat format)
public static boolean deleteNon_EmptyDir(File dir)


```

