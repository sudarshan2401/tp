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
   - [Adding a class](#adding-a-class--add)
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

Coming soon...

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

| Action     | Format, Examples                                                                                                                                                                                                           |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**    | **Add class:** <br> **Format:** `add /c CLASS_NAME` <br> **Eg:** `add /c CS2103T` <br> <br> **Add student:** <br> **Format:** `add /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `add /s John /c CS2103T`                   |
| **remove** | **Remove class:** <br> **Format:** `remove /c CLASS_NAME` <br> **Eg:** `remove /c CS2103T` <br> <br> **Remove student:** <br> **Format:** `remove /s STUDENT_NAME  /c CLASS_NAME` <br> **Eg:** `remove /s John /c CS2103T` |
| **view**   | **View class:** <br> **Format:** `view /c CLASS_NAME` <br> **Eg:** `view CS2103T`                                                                                                                                          |
