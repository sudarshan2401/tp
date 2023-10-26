---
layout: default.md
title: "User Guide"
pageNav: 3
---

# EduTrack User Guide

EduTrack is a **desktop app for managing classes designed for tutors**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Whether you are managing multiple students or just need quick access to individual student data, EduTrack empowers you with effortless control and organization.

If you are new to this guide, click [here](#about) to find out more about this user guide.

If you are keen to get started on using EduTrack, click [here](#quick-start) for our quick start guide.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

1. [Quick start](#quick-start)
2. [About](#about)
   - [Structure of this user guide](#structure-of-this-user-guide)
   - [Reading this user guide](#reading-this-user-guide)
     - [Common icons](#common-icons)
     - [Common terms](#common-terms)
     - [Command Format](#command-format)
     - [Command Parameters](#command-parameters)
2. [Features](#features)
    - [Class Commands](#class-commands)
      - [Adding a class : `add`](#adding-a-class--add)
      - [Removing a class : `remove`](#removing-a-class--remove)
      - [Viewing a class : `view`](#viewing-a-class--view)
      - [Editing a class: `view`](#editing-a-class--edit)
    - [Student commands](#student-commands)
      - [Adding a student : `add /s`](#adding-a-student--add-s)
      - [Removing a student : `remove`](#removing-a-student--remove)
      - [Editing a student : `edit /s`](#editing-a-student--edit-s)
    - [Miscellaneous commands](#miscellaneous-commands)
      - [Starting a lesson : `startlesson`](#starting-a-lesson--startlesson)
      - [Viewing help : `help`](#viewing-help--help)
      - [Exiting the program : `exit`](#exiting-the-program--exit)
      - [Saving the data](#saving-the-data)
3. [FAQ](#faq)
4. [Known issues](#known-issues)
5. [Command summary](#command-summary)
6. [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `EduTrack.jar` from [here](https://github.com/AY2324S1-CS2103T-T15-3/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your EduTrack.
4. Double-click the file to start the app.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br> ![Ui](images/Ui.png)
5. Type the command in the command box and press `Enter` to execute it. eg. typing **`help`** and `Enter` again will open the help window.<br>
   Some example commands you can try:
   * `list` : Lists all classes.
   * `view /c 1` : Lists all students in the first class in the list.
   * `add /c CS2103T` : Adds a class named `CS2103T` to the list of classes.
   * `add /s John /c 1` : Adds a student named `John` to the first class in the list.
   * `remove /c 1` : Removes the first class in the list.
   * `exit` : Exits the app.

--------------------------------------------------------------------------------------------------------------------

## About

### Structure of this user guide
This guide is structured in a manner that lets you find what you need fast and easily. To jump to various sections, you can refer to the [Table of Contents](#table-of-contents).

In the following subsection, you can find several tips that could be beneficial when reading this guide. The next section, documents the _main features_ that **EduTrack** offers and provides you instructions on how to use them!

### Reading this user guide

#### Common icons
Here are the common icons that is used throughout this user guide.

**Additional Information**<br>
Text that appear in an information box indicates additional information that is useful to know.

<box type="info">
    **Information:** Example additional information.
</box>

**Caution**<br>
Text that appear in a caution box should be followed carefully, else unintended consequences might arise.

<box type="warning">
    **Caution:** Example warning. 
</box>

**Tip**<br>
Text that appear in a tip box are useful for improving your user experience with EduTrack.

<box type="tip">
    **Tip:** Example tip.
</box>

#### Common terms
Here are the common terms that is used throughout this user guide.

| Terms       | Meaning                                                                        |
|-------------|--------------------------------------------------------------------------------|
| Command     | The instruction you enter into the application                                 |
| Parameter   | The additional information you provide for the instruction you intend to enter |

#### Command Format

_Coming soon..._

#### Command Parameters

_Coming soon..._

return to [Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features

The features are broken down into their following subsections:
1. [Class commands](#class-commands)
2. [Student commands](#student-commands)
3. [Miscellaneous commands](#miscellaneous-commands)

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are compulsory parameters to be supplied by the user.<br>
  e.g. in `add /c CLASS_NAME`, `CLASS_NAME` is a parameter which can be used as `add /c CS2103T-T15-3`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>


### Class commands

### Adding a class : `add`

Adds a new class to the list of classes.

**Command Format:** `add /c CLASS_NAME`

* CLASS_NAME is not case-sensitive

* CLASS_NAME must be unique (cannot have two classes with the same name)

**Examples:**
* `add /c cs2103t`

**Successful Output:**
* CLASS_NAME has been added

**Unsuccessful Output:**
* CLASS_NAME already exists
* Class name not specified

### Removing a class : `remove`

Removes an existing class from the list of classes.

**Command Format:** `remove /c CLASS_NAME`

* CLASS_NAME is not case-sensitive
* Class must already exist

> [!Confirmation is required to remove your class]\
> Are you sure you want to remove class "CLASS_NAME?"\
> This action cannot be undone\
> Type 'y' to confirm and 'n' to cancel

**Examples:**
* `remove /c cs2103t`

**Successful Output:**
* CLASS_NAME has been removed

**Unsuccessful Output:**
* CLASS_NAME does not exist
* Class name not specified

### Viewing a class : `view`

Prints out the detailed information (class name, class schedule, enrolled student list) about a class.

**Command Format**: `view /c CLASS_INDEX`
* Index must be within the range of the list of classes
* Class must already exist

**Examples**:
* `view /c 1`
* `view /c 2`

**Successful Output:**
* UI updated to show class information and student list

**Unsuccessful Output:**
* The class index provided is invalid

### Editing a class : `edit`

Edits a class information (class name, class memo, class schedule) at the specified index.

Command Format: `edit /c CLASS_INDEX /n NEW_CLASS_NAME /m NEW_MEMO /t NEW_SCHEDULE`

* User must specify at least one parameter (`/n`, `/m` or `/t`)
* CLASS_INDEX must be valid positive integer shown in displayed class list
* CLASS_SCHEDULE if non-blank, then follows the format: `day, HH:MM-HH:MM` (e.g: tue, 09:00-10:00)
* Existing values will be updated to the input values
* To delete class memo or class schedule, you can type `/m` or `/t` without specifying any tags after it

**Examples:**
* edit /c 1 /n cs2100
* edit /c 1 /m submit attendance report by tomorrow /n cs2100
* edit /c 1 /t mon, 10:00-12:00 /m

**Successful Output:**
Edited class: NEW_CLASS_NAME
Schedule: NEW_SCHEDULE
Memo: NEW_MEMO

**Unsuccessful Output:**
* Class does not exist
* Class already exists

### Student commands

### Adding a student : `add /s`

Adds a new student to an existing class.

Command Format: `add /s STUDENT_NAME /c CLASS_INDEX`

Examples:
* `add /s John /c 1`
* `add /s Bob /c 2`

<box type="info">

**Information:**
* Student name can only be alphabetical letters.
* The index refers to the index number shown in the displayed class list.
* The index **must be a positive integer** 1, 2, 3, ...
* A default id "A0000000Z" will be assigned to the student.

</box>

<box type="warning">

**Caution:**
* The class to add the student into must already exist.

</box>

<box type="tip">

**Tip:**
* You can edit the student's id using the [edit student command](#editing-a-student--edit-s) once you know the student's student number!

</box>

Successful Output:

```Added New Student: student_name; Id: A0000000Z; Memo:  to the class: CS```


### Removing a student : `remove`

Removes an existing student from a class based on index.

**Command Format**: `remove /s STUDENT_INDEX /c CLASS_NAME`

* CLASS_NAME is not case-sensitive
* Class must already exist

**Examples:**
* `remove /s 1 /c CS2103T`

**Successful Output:**
* STUDENT_NAME has been removed from CLASS_NAME

**Unsuccessful Output:**
* Class name not specified
* CLASS_NAME does not exist
* Student index provided is invalid

### Editing a student : `edit /s`

Edits an existing student from a specified class.

Command Format: `edit /s STUDENT_INDEX /c CLASS_NAME [/nName] [/idId] [/mMemo]`

Examples:
* `edit /s 1 /c CS2103T /nBob` : Edits the name of the first student in the class CS2103T to Bob.
* `edit /s 2 /c CS2103T /idA0231234U` : Edits the id of the second student in the class CS2103T to "A0231234U".

Sample Usage:

1. Assuming you want to edit the 2nd Student's Id to "A010193Z", who is from the Class "CS2103T", and the class index in the displayed list is "1".
2. Enter the following commands:
   ````
   view /c 1
   edit /s 2 /c CS2103T /idA010193Z
   ````
3. The result box will display the following message:
   ````
   Edited Student: John; Id: A010193Z; Memo: 
   ````
4. You have successfully edited the student's details.

<box type="info">

**Information:**
* Edits the student at the specified `INDEX` in the specified `CLASS_NAME`.
* The index refers to the index shown in the displayed student list of the class.
* The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

</box>

<box type="caution">

**Caution:**
* You have to perform a [view class command](#viewing-a-class--view) first!
* Only valid ids will be recognised. It has to start with "A", followed by only numerical characters and end of with "A-Z".

</box>

### Starting a lesson : `startlesson`

Starts a lesson of a class.

**Command Format**: `startlesson /c CLASS_NAME`

* CLASS_NAME is not case-sensitive
* Class must already exist

**Examples:**
* `startlesson /c CS2103T`

**Successful Output:**
* CLASS_NAME started a new lesson!

**Unsuccessful Output:**
* Class name not specified
* CLASS_NAME does not exist

### Miscellaneous commands

### Viewing help : `help`

Shows a message explaining how to access the help page.

**Command Format:** `help`

### List all classes : `list`

Shows a list of all existing classes.

**Command Format:** `list`

### Exiting the program : `exit`

Exits the program.

**Command Format:** `exit`

### Saving the data

EduTrack's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file EduTrack creates, with the file
that contains the data of your previous EduTrack home folder.

**Q**: How do I save my data?<br>
**A**: EduTrack's data is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Known issues

Coming soon...

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                            |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**    | **Adding a class:** <br> **Format:** `add /c CLASS_NAME` <br> **Eg:** `add /c CS2103T` <br> <br> **Adding a student:** <br> **Format:** `add /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `add /s John /c CS2103T`  <br><br> **Adding a list of students** <br> Coming soon |
| **remove** | **Removing a class:** <br> **Format:** `remove /c CLASS_NAME` <br> **Eg:** `remove /c CS2103T` <br> <br> **Removing a student:** <br> **Format:** `remove /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `remove /s John /c CS2103T`                                          |
| **view**   | **Viewing a class:** <br> **Format:** `view /c CLASS_INDEX` <br> **Eg:** `view /c 1`                                                                                                                                                                                           |
| **edit**   | **Editing a class:** <br> **Format:** `edit /c CLASS_INDEX /n NEW_CLASS_NAME /m NEW_CLASS_MEMO /t NEW_CLASS_SCHEDULE` <br> **Eg:** `edit /c 2 /m submit marking report`                                                                                                     |

--------------------------------------------------------------------------------------------------------------------

## Glossary
| Term                               | Definition                                                                                                                                           |
|------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Command Line Interface (CLI)**   | Command line interface where users interact with the system by typing in commands. <br> <br> e.g. Terminal                                           |
| **Graphical User Interface (GUI)** | Graphical user interface where users interact with the system through visual representations. <br> <br> e.g. Google Chrome, Spotify, Windows Desktop |
