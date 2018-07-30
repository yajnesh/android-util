# android-util
Most used short code snippets generically designed. 
The goal is to avoid `NullPointerException` and `IndexOutOfBoundException` , still providing meaningful outputs.
All the methods in this library accepts `Nullable`s and gracefully performs alternate actions defined.


# General Utilities

This is overall top level usage doc, detailed docs are present as javadoc within code files


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
## 5)  Get String

Get string from given object


####  ``` GenericUtil.getString(something);```
> This will return string representaion of the parameter

parameter **something** can be any of the following
- Any Object
- EditText

**Return value** will be 

- String contained under the object

<br/>

## 6)  Validations

Validate common string patterns


#### **a)** ``` GenericUtil.isValidEmail(probableEmail);```
> This will return true if the parameter is a valid email

parameter **something** can be any of the following
- String

**Return value** will be 

- True, if the parameter is a valid email

<br/>


#### **b)** ``` GenericUtil.isValidPhone(probableEmail);```
> This will return true if the parameter is a valid phone number

parameter **something** can be any of the following
- String

**Return value** will be 

- True, if the parameter is a valid email

<br/>

## 7)  json-to-collection conversion and vice-versa

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

## 8)  App Info

Version related info

#### **a)** ``` GenericUtil.getAppVersion(context);```
# java-util
Most used short code snippets generically designed


# General Utilities

This is overall top level usage doc, detailed docs are present as javadoc withing code files

## 1)  Safely get item from any Sequence

Friendly APIs to get item from any Array/Collection/ArraySet.
It is bettet to get null instead of crashing with `NullPointerException` or `IndexOutOfBoundException`

<br/>

#### **a)** ``` GenericUtil.get(sequence, 5);```
> This will try to get 6th item from array/collection.

**Sequence** can be any of the following
- Array
- List
- ArraySet



**Return value** will be 

- In case array/collection is null, null will be returned instead of crashing
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
- String
- EditText

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

**Return value** will be 

- In case parameter is null, 0 will be returned
- if not null, then based on the underlaying datastructure, size will be returned

<br/>


## 4)  Equals

Compare two obects for equality


####  ``` GenericUtil.equals(a,b);```
> This will return true if both objects are equal

parameter **something** can be any of the following
- Any Object

**Return value** will be 

- In case of **either** parameter is null, false will be returned
- In case of **both** parameters are null, false will be returned
- If objects are equal , true will be returned, false otherwise

<br/>

## 5)  Get String

Get string from given object


####  ``` GenericUtil.getString(something);```
> This will return string representaion of the parameter

parameter **something** can be any of the following
- Any Object
- EditText

**Return value** will be 

- String contained under the object

<br/>

## 6)  Validations

Validate common string patterns


#### **a)** ``` GenericUtil.isValidEmail(probableEmail);```
> This will return true if the parameter is a valid email

parameter **something** can be any of the following
- String

**Return value** will be 

- True, if the parameter is a valid email

<br/>


#### **b)** ``` GenericUtil.isValidPhone(probableEmail);```
> This will return true if the parameter is a valid phone number

parameter **something** can be any of the following
- String

**Return value** will be 

- True, if the parameter is a valid email

<br/>

## 7)  json-to-collection conversion and vice-versa

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

## 8)  App Info

Version related info

#### **a)** ``` GenericUtil.getAppVersion(context);```

> Get the app version

<br/>

#### **b)** ``` GenericUtil.getAppVersionName(context);```

> Get the app versionName

<br/>

> Get the app version

<br/>

#### **b)** ``` GenericUtil.getAppVersionName(context);```

> Get the app versionName

<br/>
