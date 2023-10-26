---
layout: default.md
title: "User Guide"
pageNav: 3
---

# EduTrack User Guide

EduTrack is a **desktop app for managing classes designed for tutors**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Whether you are managing multiple students or just need quick access to individual student data, EduTrack empowers you with effortless control and organization.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
    - [Viewing help : `help`](#viewing-help--help)
    - [Adding a class : `add`](#adding-a-class--add)
    - [Removing a class : `remove`](#removing-a-class--remove)
    - [Viewing a class : `view`](#viewing-a-class--view)
    - [Editing a class: `view`](#editing-a-class--edit)
    - [Adding a student : `add`](#adding-a-student--add)
    - [Removing a student : `remove`](#removing-a-student--remove)
    - [Starting a lesson : `startlesson`](#starting-a-lesson--startlesson)
    - [Marking a student present : `mark`](#marking-your-students-attendance-as-present--mark)
    - [Marking a student absent : `unmark`](#marking-all-students-attendance-in-your-class-as-present--markall)
    - [Marking all students present : `markall`](#marking-your-students-attendance-as-absent--unmark)
    - [Exiting the program : `exit`](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
    - [Adding a list of students : `coming in v1.3`](#adding-a-list-of-students--coming-in-v13)
    - [Updating a student : `coming in v1.3`](#updating-a-student--coming-in-v13)
    - [Adding a lesson : `coming in v1.3`](#adding-a-lesson--coming-in-v13)
    - [Removing a lesson : `coming in v1.3`](#removing-a-lesson--coming-in-v13)
    - [Finding a student : `coming in v1.3`](#finding-a-student--coming-in-v13)
3. [FAQ](#faq)
4. [Known issues](#known-issues)
5. [Command summary](#command-summary)
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 or above install in your Computer. (Refer to this <a href="https://www.codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk">installation guide</a> if you have yet to do so)
2. Download the latest `EduTrack.jar` from <a href="https://github.com/AY2324S1-CS2103T-T15-3/tp/releases/download/v1.3.trial/EduTrack.jar">here</a>.
3. Copy the file to the folder you want to use as the home folder for your EduTrack.
4. Open a command terminal, `cd` into the folder you placed your `EduTrack.jar` file in, and use the `java -jar EduTrack.jar` command to run the application.<br/>A GUI similar to the below should appear in a few seconds. Note how the application contains some sample data.<br/><img src="images/quickstart.png" alt="EduTrackimage"></img>
5. Type the command in the command box and press Enter to execute it. eg. typing `help` and pressing Enter will open the help window.
   Some example commands you can try:
   - list: Lists all your classes.
   - `add /c CS2105`: Adds a class named `CS2105` to your EduTrack.
   - `add /s Samuel /c 1`: Adds a student named `Samuel` to the class at index `1` which you can find when you `list` your classes.
   - `view /c 1`: View your students in your `CS2105` class.
   - `mark /s 1 /c CS2105`: Mark the attendance of your student at index `1` in your `CS2105` class.
6. Refer to the [Features](#features) below for details of each command.
 
--------------------------------------------------------------------------------------------------------------------

## Features

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


### Viewing help : `help`

Shows a message explaining how to access the help page.

**Command Format:** `help`

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

**Command Format:** `remove /c CLASS_INDEX`

* CLASS_INDEX must be a valid positive integer shown in the displayed class list
* CLASS_NAME must be the name of a class that already exists

**Examples:**
* `remove /c 1`

**Successful Output:**
* CLASS_NAME has been removed

**Unsuccessful Output:**
* CLASS_NAME does not exist
* Class name not specified

### Viewing a class : `view`

Prints out the detailed information (class name, class schedule, enrolled student list) about a class.

**Command Format**: `view /c CLASS_NAME`
* CLASS_NAME is not case-sensitive
* Class must already exist

**Examples**:
* `view CS2103T`
* `view cs2103t`

**Successful Output:**
* CLASS_NAME: Enrolled students: (...)

**Unsuccessful Output:**
* CLASS_NAME does not exist

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

### Adding a student : `add`

Adds a new student to an existing class.

**Command Format**: `add /s STUDENT_NAME /c CLASS_NAME`
* CLASS_NAME is not case-sensitive
* Class must already exist
* STUDENT_NAME only accepts alphabetical characters

**Examples**:
* `add /s John /c cs2103t`

**Successful Output**:
* STUDENT_NAME has been added to CLASS_NAME

**Unsuccessful Output**:
* Class name not specified
* CLASS_NAME does not exist

### Removing a student : `remove`

Removes an existing student from a class based on index.

**Command Format**: `remove /s STUDENT_INDEX /c CLASS_NAME`

* CLASS_NAME is not case-sensitive
* Class must already exist

**Examples:**
* Remove /s 1 /c CS2103T

**Successful Output:**
* STUDENT_NAME has been removed from CLASS_NAME

**Unsuccessful Output:**
* Class name not specified
* CLASS_NAME does not exist
* Student index provided is invalid

### Starting a lesson : `startlesson`

Starts a lesson of a class.

**Command Format**: `startlesson /c CLASS_NAME`

* CLASS_NAME is not case-sensitive
* Class must already exist

**Examples:**
* `startlesson /c CS2103T`

**Successful Output:**
* CS2103T started a new lesson!

**Unsuccessful Output:**
* Class name not specified
* CLASS_NAME does not exist

### Marking your student's attendance as present : `mark`

**Command Format**: `mark /s STUDENT_INDEX /c CLASS_NAME`

* Both STUDENT_INDEX and CLASS_NAME are compulsory
* STUDENT_INDEX must be a valid positive integer shown in the displayed student list
* CLASS_NAME is not case-sensitive
* CLASS_NAME must be the name of a class that already exists
* Selected should have been marked absent

**Examples**
* mark /s 2 /c CS2103T

**Successful Output:**
* STUDENT_NAME has been marked present!

**Unsuccessful Output:**
* Index is not a non-zero unsigned integer
* The Class name (CS9999) you provided does not exist!
* STUDENT_NAME has already been marked present!

### Marking your student's attendance as absent : `unmark`

**Command Format**: `unmark /s STUDENT_INDEX /c CLASS_NAME`

* Both STUDENT_INDEX and CLASS_NAME are compulsory
* CLASS_INDEX must be a valid positive integer shown in the displayed student list
* CLASS_NAME is not case-sensitive
* CLASS_NAME must be the name of a class that already exists
* Selected student should have been marked present

**Examples**
* unmark /s 2 /c CS2103T

**Successful Output:**
* Emily has been marked absent!!

**Unsuccessful Output:**
* Index is not a non-zero unsigned integer
* The Class name (CS9999) you provided does not exist!
* Emily has already been marked absent!

### Marking all student's attendance in your class as present : `markall`

**Command Format**: `markall /c CLASS_INDEX`

Marks all student in your class as being present. Use this if all your students are present for your current lesson.

* CLASS_INDEX is compulsory
* CLASS_INDEX must be a valid positive integer shown in the displayed class list

**Examples**
* markall /c 1

**Successful Output:**
* Successfully marked all students present in CLASS_NAME!

**Unsuccessful Output:**
* Class index provided is invalid

### Exiting the program : `exit`

Exits the program.

**Command Format:** `exit`

### Saving the data

EduTrack's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Adding a list of students : `coming in v1.3`
[Coming soon...]

### Updating a class : `coming in v1.3`
[Coming Soon...]

### Updating a class memo : `coming in v1.3`
[Coming Soon...]

### Updating a student : `coming in v1.3`
[Coming Soon...]

### Adding a lesson : `coming in v1.3`
[Coming Soon...]

### Removing a lesson : `coming in v1.3`
[Coming Soon...]

### Finding a student : `coming in v1.3`
[Coming Soon...]

--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming soon...

--------------------------------------------------------------------------------------------------------------------

## Known issues

Coming soon...

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format, Examples                                                                                                                                                                                                                                                            |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**     | **Adding a class:** <br> **Format:** `add /c CLASS_NAME` <br> **Eg:** `add /c CS2103T` <br> <br> **Adding a student:** <br> **Format:** `add /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `add /s John /c CS2103T`  <br><br> **Adding a list of students** <br> Coming soon |
| **remove**  | **Removing a class:** <br> **Format:** `remove /c CLASS_NAME` <br> **Eg:** `remove /c CS2103T` <br> <br> **Removing a student:** <br> **Format:** `remove /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `remove /s John /c CS2103T`                                          |
| **view**    | **Viewing a class:** <br> **Format:** `view /c CLASS_NAME` <br> **Eg:** `view CS2103T`                                                                                                                                                                                      |
| **edit**    | **Editing a class:** <br> **Format:** `edit /c CLASS_INDEX /n NEW_CLASS_NAME /m NEW_CLASS_MEMO /t NEW_CLASS_SCHEDULE` <br> **Eg:** `edit /c 2 /m submit marking report`                                                                                                     |
| **mark**    | **Marking your student present:** <br> **Format:** `mark /s STUDENT_INDEX /c CLASS_NAME` <br> **Eg:** `mark /s 1 /c CS2103T`                                                                                                                                                |
| **unmark**  | **Marking your student absent:** <br> **Format:** `unmark /s STUDENT_INDEX /c CLASS_NAME` <br> **Eg:** `unmark /s 1 /c CS2103T`                                                                                                                                             |
| **markall** | **Marking all your students in a class present:** <br> **Format:** `markall /c CLASS_INDEX` <br> **Eg:** `markall /c 1`                                                                                                                                                     |
