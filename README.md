TranslateEverywhere
===================

This tool aims to facilitate the sharing of files between different language platforms.


## Motivation

In the context of mobile applications you may have to transform iOS language file to Android language. Of course, you can make series of copy and paste but it quickly becomes tedious. And more if your app support many languages

Yes convertors like https://localise.biz/free/converter/ios-to-android already exist and you may have :

iOS file :

```
"APP_NAME"="Trololo";
"A_FUCKING_KEY"="Settings";
```

become :
```
<string name="APP_NAME">Trololo</string>
<string name="A_FUCKING_KEY">Settings</string>
```

Two problems :
* On Android by convention string names are low cased
* You are not agree with the name

 
Then, you may want to organize your code in file and by group like it :
```xml
<!-- strings.xml -->
<string name="title_email">Email</string>
<string name="title_password">Password</string>

<string name="hint_email">Type your email</string>
<string name="hint_password">Please, don't type 123456...</string>
```
```xml
<!-- strings_activity_settings.xml -->
<string name="settings">Settings</string>
```
 
 
## Quick start
 
First, you have to create a file which make the link between android string names and ios string names (or any other supported format) like it :
 
```
## [output_file_name]
[strings name]=[his correspondence]
[another string name]=[other correspondence or the same]

[string name]=?
## [new_output_file_name]
[strings name]=[his correspondence]
```

If we take our above example : 
```
## strings.xml
title_email=IOS_TITLE_EMAIL
title_password=IOS_TITLE_PASSWD

hint_email=IOS_HINT_EMAIL
hint_passwd=?
## strings_activity_settings.xml
settings=A_FUCKING_KEY
```

Each ```##``` will create a new file and next strings will be write in.
Each blank line will create a blank line in the output file.
You can specify that a strings don't have correspondence with ```?```, they will be useful in the future


Well, we have make the most boring. Now we just have to type one command (trew is the program name) : 
```shell
$ trew [convert_type] [link_file] [input_files, ...] [output directory]
```

Arguments : 
* convert_type : currently ```i2a``` (iOS to Android) or ```a2i``` (Android to iOS)
* link_file : file which make the correspondence between them 
* input_files : your source files with string names and their values
* output_directory : directory where generated file will be written


An example ?
```shell
$ trew i2a my_link_file.tew Localizable_fr.strings values-fr
$ ls values-fr
strings.xml strings_activity_settings.xml
$ cat values-fr/strings_activity_settings.xml
<resource>
	<strings name="settings">Paramètres</string>
<resource>
```

That's all !


Trick : trew can generate for you skeleton of link file :
```shell
$ trew skeleton [type_file] [input_files, ...]
```

Arguments :
* type_file : currently ```android``` or ```ios```
* input_files : your source file with string name to analyse and reproduce


## Download

beta : https://mega.co.nz/#!Ux4WBIQS!erOUW0aVu4QHrb2Yomfn7bGJP3zeXbC8T2ClrR52YYw


## Credit
Author : Nicolas Barranger (wicowyn@gmail.com)

<a href="https://plus.google.com/+NicolasBarranger/posts">
  <img alt="Follow me on Google+"
       src="https://github.com/gabrielemariotti/cardslib/raw/master/demo/images/g+64.png" />
</a>
<a href="https://twitter.com/Wicowyn">
  <img alt="Follow me on Twitter"
       src="https://github.com/gabrielemariotti/cardslib/raw/master/demo/images/twitter64.png" />
</a>

