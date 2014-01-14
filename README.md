CommonlyUsedUtils
=================

These are some of the Utils that I find myself writing every single time I write a new application.

This is only a java file that you can copy into your projects.


//Contructor
public CommonlyUsedUtils(FragmentActivity act) {
		mActivity = act;
		mPrefs = PreferenceManager.getDefaultSharedPreferences(mActivity);
		editor = mPrefs.edit();
}

Methods:
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

//Different Intents

public void callAPhone(String phoneNumber)
public void GetDirectionsTo(String Address)
public void GoToWebsite(String websiteURL)
public void CreateEmail(String[] recipients, String Title, String Text)

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

public String longDateString(Date date)
