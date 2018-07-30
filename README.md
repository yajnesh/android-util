# android-util
Most used short code snippets generically designed. 
The goal is to avoid `NullPointerException` and `IndexOutOfBoundException` , still providing meaningful outputs.
All the methods in this library accepts `Nullable`s and gracefully performs alternate actions defined.

This is overall top level usage doc, detailed docs are present as javadoc within code files


Gradle:
```groovy
compile 'in.yajnesh.util:java-util:1.0.0' //required for android-util
compile 'in.yajnesh.util:android-util:1.0.0'
```


# General Utilities

<br/>


## 1)  Safely get item from any Sequence

Friendly APIs to get item from any Sequence.
It is bettet to get null instead of crashing with `NullPointerException` or `IndexOutOfBoundException`

<br/>

#### **a)** ``` GenericUtil.get(sequence, 5);```
> This will try to get 6th item from sequence.

**Sequence** can be any of the following
- Array
- List
- String
- CharSequence
- ArraySet
- ViewGroup
- JSONArray



**Return value** will be 

- In case sequence is null, null will be returned instead of crashing
- In case of index is above the size of negative index, null will be returned instead of crashing

<br/>

#### **b)**  ``` GenericUtil.get(array/collection, 5, defaultValue);```

This is similar to the above api, but instead of null values, this will return defaultvalue provided

<br/> <br/>
## 2)  Check if an something is empty

Friendly APIs to identify if elements/sequence is empty.


####  ``` GenericUtil.isEmpty(something);```
> This will return true if something is empty or null

parameter **something** can be any of the following
- Array
- Collection
- CharSequence
- Map
- String
- EditText
- TextView
- Editable
- JSONArray
- Bundle
- PagerAdapter

**Return value** will be 

- In case parameter is null, true will be returned instead of crashing
- In case parameter is empty(decided by underlaying datastructure) , return true
- Return false, in all other cases

<br/>

## 3)  Get the size of something

Friendly APIs to get the size of element/sequence.


####  ``` GenericUtil.size(something);```
> This will return the size of something 

parameter **something** can be any of the following
- Array
- Collection
- String
- CharSequence
- ViewGroup
- ArraySet
- Bundle
- JSONArray

**Return value** will be 

- In case parameter is null, 0 will be returned
- if not null, then based on the underlaying datastructure, size will be returned

<br/>


## 4)  Safely get last item from any Sequence

Friendly APIs to get item from any Sequence.
It is bettet to get null instead of crashing with `NullPointerException` or `IndexOutOfBoundException`

<br/>

#### **a)** ``` GenericUtil.getLastItem(sequence);```
> This will try to get last item from sequence.

**Sequence** can be any of the following
- Array
- List
- String
- CharSequence
- ArraySet
- ViewGroup
- JSONArray



**Return value** will be 

- In case sequence is null, null will be returned instead of crashing
- In case of index is above the size of negative index, null will be returned instead of crashing

<br/>

#### **b)**  ``` GenericUtil.getLastItem(sequence, defaultValue);```

This is similar to the above api, but instead of null values, this will return defaultvalue provided

<br/> <br/>



## 5)  Equals

Compare two obects for equality

#### **a)**  ``` GenericUtil.equals(a,b);```
> This will return true if both objects are equal

parameter **something** can be any of the following
- Any Object

**Return value** will be 

- In case of **either** parameter is null, false will be returned
- In case of **both** parameters are null, false will be returned
- If objects are equal , true will be returned, false otherwise

<br/>

#### **b)**  ``` GenericUtil.equalsRelaxed(a,b);```

This is similar to the above api, but ignores the cases while comparing

<br/> <br/>


## 6) Contains

Does source has searchTerm in it?

#### **a)**  ``` GenericUtil.contains(source,searchTerm);```
> true if searchTerm is found in source, otherwise false. Also false if either or both params are null

<br/>

#### **b)**  ``` GenericUtil.containsIgnoreCase(source,searchTerm);```

This is similar to the above api, but ignores the cases while checking

<br/> <br/>


## 7) TypeCast

TypeCast and object to passed class

####  ``` GenericUtil.typeCast(object,Integer.class);```
>In this case, safely typecasts object to Integer and returns an Integer

<br/>


## 8) Stringify

Get string out of Collection

#### **a)**  ``` GenericUtil.stringify(collection/map);```
> returns string representation of collection/map

<br/>

#### **b)**  ``` GenericUtil.stringify(preText,collection/map);```

This is similar to the above api, adds preText infront of the string representaion

<br/> <br/>

## 9) Is Any Empty

 Is any object passed is null or empty.<br>

####  ``` GenericUtil.isAnyEmpty(string1,string2,string3,string4);```
####  ``` GenericUtil.isAnyEmpty(collection,map,string,charSequence,jsonArray,object);```
>returns true, if any of the object passed is null or empty, false otherwise

<br/>

## 10) Are All Empty

 Are all the object passed is null or empty.<br>

####  ``` GenericUtil.isAllEmpty(string1,string2,string3,string4);```
####  ``` GenericUtil.isAllEmpty(collection,map,string,charSequence,jsonArray,object);```
>returns true, if all of the object passed is null or empty, false otherwise

<br/>

## 11)  Get String

Get string from given object

####   **a)** ``` GenericUtil.getString(something);```
> This will return string representaion of the parameter

parameter **something** can be any of the following
- Any Object
- EditText
- TextView

**Return value** will be 

- String contained under the object, or null

####   **b)** ``` GenericUtil.getStringSafe(something);```
> This is same as above, but returns empty string in case input in null

<br/>



## 12) Safe url encode

 Translates a string into x-www-form-urlencoded.<br>

####  ``` GenericUtil.safeUrlEncode(string,encoding);```

>returns the encoded url

<br/>

## 13)  Validations

Validate common string patterns


#### **a)** ``` GenericUtil.isValidEmail(probableEmail);```
> This will return true if the parameter is a valid email

**Return value** will be 

- True, if the parameter is a valid email

<br/>


#### **b)** ``` GenericUtil.isValidPhone(probablePhoneNumber);```
> This will return true if the parameter is a valid phone number

**Return value** will be 

- True, if the parameter is a valid phone number

<br/>

## 14)  App Info

Version related info

#### **a)** ``` GenericUtil.getAppVersion(context);```
#### **b)** ``` GenericUtil.getAppVersionName(context);```

## 14)  Html to Spanned

Returns displayable styled text from the provided HTML string.

#### ``` GenericUtil.getSpannedFromHtml(context);```

<br/> <br/>

# Log Utilities

Better Logger class with multipurpose use. Supports the following types of logs. Notice that tag is omitted, calling class name is automatically detected and put as tag. All of these operations are ```null``` safe.

## Common Log
####  Log with auto tag
```ALog.v(msg);```  <br/>
```ALog.i(msg);```  <br/>
```ALog.d(msg);```  <br/>
```ALog.w(msg);```  <br/>
```ALog.e(msg);```  <br/>
```ALog.wtf(msg);```  <br/>
retrns the msg itself.

####  Log with message extracted from string resource id
```ALog.e(context,resId);```  <br/>
retrns the string msg. <br/>

####  Log with message and throwable
```ALog.e(msg,throwable);```  <br/>
retrns the msg itself.

## File Log

#### Enable file logging
```ALog.enableFileLogging(true)```  <br/>
This will write(append) all logs to a file<br/>

#### Set log file name
```ALog.setLogFileName("myLog.txt");```<br/>
filename or filePath, Eg: "myLog.txt" , "my/directory/myLog.txt"<br/>

## Other Log utils

#### Set log level
```ALog.setLogLevel(LogLevel.NONE)```<br/>
only the set log level and the levels above it will be printed.<br/>

Possible values are, <br/>
- LogLevel.WTF
- LogLevel.ERROR
- LogLevel.WARN
- LogLevel.INFO
- LogLevel.DEBUG
- LogLevel.VERBOSE
- LogLevel.NONE

#### Enable/Disable auto detection of caller class
```ALog.enableAutoDetectionOfCaller(true/false);```

#### Prefix
Prefix to be added before each log.
```ALog.setLogPrefix(string);```

#### Write to file without logging
```ALog.writeToFile(string);```

## Toast

#### Toast with string
Show a short toast<br/>
```ALog.toast(context,string);```

#### Toast with resId
Show a short toast<br/>
```ALog.toast(context,resId);```


# Conversion Utils
## json-to-collection conversion and vice-versa

Convert most common collection to json and vice-versa

#### **a)** ``` GenericUtil.mapToJson(map);```

> Converts map to json

parameter **map** can be any of the following
- Any Map

**Return value** will be 

- Converted json data

<br/>

#### **b)** ``` GenericUtil.jsonToMap(json);```

> Converts json object to map

parameter **something** can be any of the following
- String representation of json
- JSONObject

**Return value** will be 

- Converted Map

<br/>

#### **c)** ``` GenericUtil.setToJson(set);```

> Converts set to json array

parameter **set** can be any of the following
- Any Set

**Return value** will be 

- Converted json array

<br/>

#### **d)** ``` GenericUtil.jsonToSet(json);```

> Converts json array to set

parameter **something** can be any of the following
- String representation of json array
- JSONArray

**Return value** will be 

- Converted Set

<br/>

# Many other classes documentation coming soon, Please look at the javadoc within the code till then

