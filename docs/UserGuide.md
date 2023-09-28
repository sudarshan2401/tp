---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# EduTrack User Guide

EduTrack is a **desktop app for managing classes designed for tutors**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Whether you're juggling multiple students or just need quick access to individual student data, EduTrack empowers you with effortless control and organization.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

1. [Quick start](#quick-start)
2. [Features](#features)
    - [Viewing help](#viewing-help--help-coming-in-v13)
    - [Adding a class](#adding-a-class--add)
    - [Adding a list of students](#adding-a-list-of-students--add)
    - [Removing a student](#removing-a-student--remove)
3. [FAQ](#faq)
4. [Known issues](#known-issues)
5. [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

Coming soon...

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add /c CLASS_NAME`, `CLASS_NAME` is a parameter which can be used as `add /c CS2103T-T15-3`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Adding a class : `add`

Adds a new class to the list of classes.

**Command Format:** `add /c CLASS_NAME`

* CLASS_NAME is not case-sensitive

* CLASS_NAME must be unique (cannot have two classes with the same name)

**Example:**
add /c cs2103t

**Successful Output:**
“CLASS_NAME” has been added

**Unsuccessful Output:**
“CLASS_NAME” already exists
Class Name not specified

### Removing a class : `remove`

Remove a specific class from your list of classes.

**Command Format:** `remove /c CLASS_NAME`

* Confirmation is required to remove your class:

  <span style="color:red;">
    Are you sure you want to remove class "CLASS_NAME"
  <br>
    This action cannot be undone
  <br>
    Type 'y' to confirm and 'n' to cancel
  </span>

  `y` to proceed, `n` to cancel

* CLASS_NAME is not case-sensitive

**Example:**
`remove /c cs2103t`

**Successful Output:**
“CLASS_NAME” has been removed

**Unsuccessful Output:**
* “CLASS_NAME” does not exist
* Class Name not specified

### Adding a list of students : `add`
_Coming soon_

### Removing a student : `remove`

Removes an existing student from a class.

**Command Format**: `remove /s STUDENT_NAME /c CLASS_NAME`
* STUDENT_NAME and CLASS_NAME are compulsory parameters
* CLASS_NAME is case-insensitive
* STUDENT_NAME only accepts Alphabets
* Student must have been added into the class to be removed

* CLASS_NAME must be unique (cannot have two classes with the same name)

**Example:**
Remove /s John /c CS2103T

**Successful Output:**
“STUDENT_NAME has been removed from CLASS_NAME”

**Unsuccessful Output:**
“Class name not specified”
“STUDENT_NAME is not found in CLASS_NAME”
“CLASS_NAME does not exist”

### Viewing a class : `view`
Prints out the detailed information (class name, class schedule, enrolled student list) about a specific class.
**Command Format**: `view /c CLASS_NAME`
* CLASS_NAME is not case-sensitive
* Class must have been created to be viewed

**Examples**:
view CS2103T
view cs2103t

**Successful Output:**
“CLASS_NAME”:
Enrolled students: (...)

**Unsuccessful Output:**
“CLASS_NAME” does not exist

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

EduTrack data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Updating a Class
[Coming Soon...]

### Updating a Student Profile
[Coming Soon...]

### Taking attendance for a lesson
[Coming Soon...]

### Updating a Class note
[Coming Soon...]

### Adding a lesson to a Class Schedule
[Coming Soon...]

### Removing a lesson from a Class Schedule
[Coming Soon...]

### Finding the profile of a Student from a Class
[Coming Soon...]

--------------------------------------------------------------------------------------------------------------------

## FAQ

Coming soon...

--------------------------------------------------------------------------------------------------------------------

## Known issues

Coming soon...

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                            |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**    | **Adding a class:** <br> **Format:** `add /c CLASS_NAME` <br> **Eg:** `add /c CS2103T` <br> <br> **Adding a student:** <br> **Format:** `add /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `add /s John /c CS2103T`  <br><br> **Adding a list of students** <br> Coming soon |
| **remove** | **Removing a class:** <br> **Format:** `remove /c CLASS_NAME` <br> **Eg:** `remove /c CS2103T` <br> <br> **Removing a student:** <br> **Format:** `remove /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `remove /s John /c CS2103T`                                          |
| **view**   | **Viewing a class:** <br> **Format:** `view /c CLASS_NAME` <br> **Eg:** `view CS2103T`                                                                                                                                                                                      |
