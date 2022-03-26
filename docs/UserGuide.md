---
layout: page
title: User Guide
---

TAssist is a **desktop app for managing students and their participation in lessons, optimized for use via a Command Line Interface** (CLI). If you are a TA who prefers CLI to GUI while having a GUI to view the student data, TAssist is the app for you.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TAssist.jar` from [here](https://github.com/AY2122S2-CS2103T-T13-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAssist.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list student`** and pressing Enter will list all students added.<br>
   Some example commands you can try:

   * **`list student`**: Lists all students.

   * **`add student`**`id/E0123456 n/John Doe e/johnd@example.com`: Adds a student named `John Doe` to TAssist.

   * **`delete student`**`3`: Deletes the 3rd student shown in the listing of the entity.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TELEGRAM_ID]` can be used as `n/John Doe t/john_doe` or as `n/John Doe`.

* Only one group of arguments in curly braces can be used as argument. Each group is separated by a `|`.<br>
  e.g. `{m/MODULE_INDEX | c/CLASS_GROUP_INDEX}` can be used as `m/1` or as `c/1` but not as `m/1 c/1`.

* If multiple options are accepted for a parameter, only one option can be specified.<br>
  e.g. `s/all|STUDENT_INDEXES|STUDENT_IDS` can be used as `s/all`, `s/1,2,3,4,5,6` and `s/e0123456,e0234567` but not mix-and-matched as `s/all,1,2,e0123456`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME e/EMAIL`, `e/EMAIL n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `t/john_doe t/johnny_doe`, only `t/johnny_doe` will be taken.

* Extraneous parameters for commands that do not take in parameters will be ignored.<br>
  e.g. if the command specifies `list student 123`, it will be interpreted as `list student`.

</div>

### Adding entries

#### Adding a student: `add student`

Adds a student to TAssist.

Format: `add student id/STUDENT_ID n/NAME e/EMAIL [t/TELEGRAM_ID]`

Examples:
* `add student id/E0123456 n/John Doe e/johnd@example.com` creates a new student named `John Doe` with a student ID of `E0123456` and email `johnd@example.com`.
* `add student id/E0123456 n/John Doe e/johnd@example.com t/john_doe` creates a new student named `John Doe` with a student ID of `E0123456`, email `johnd@example.com` and telegram handle `john_doe`.

#### Adding a module: `add module`

Adds a module to TAssist.

Format: `add module n/MODULE_NAME c/MODULE_CODE a/ACADEMIC_YEAR`

* `ACADEMIC_YEAR` is represented by the year (last 2 digits) and a semester.
* Semester value ranges from S1 to S8. The table below shows the representation of each value:

| Key | Semester               |
|-----|------------------------|
| S1  | Semester 1             |
| S2  | Semester 2             |
| S3  | Special Term (Part 1)  |
| S4  | Special Term (Part 2)  |
| S5  | Mini-Semester 1A       |
| S6  | Mini-Semester 1B       |
| S7  | Mini-Semester 2A       |
| S8  | Mini-Semester 2B       |

Examples:
* `add module n/Software Engineering Project c/CS2103T a/21S1` creates a new module named `Software Engineering Project` with a module code of `CS2103T` for the academic year `21S1` (academic year 2021 Semester 1).

#### Adding a class group: `add class`

Adds a class group to TAssist.

Format: `add class id/CLASS_GROUP_ID t/CLASS_GROUP_TYPE m/MODULE_INDEX`

* TAssist supports the following `CLASS_GROUP_TYPE`: `LAB`, `RECITATION`, `SECTIONAL` and `TUTORIAL`.

Examples:
* `add class id/T13 t/tutorial m/1` creates a new class group that is tied to the 1st module shown when `list module` is run.

#### Adding an assessment: `add assessment`

Adds an assessment to TAssist.

Format: `add assessment n/ASSESSMENT_NAME m/MODULE_INDEX [sn/SIMPLE_NAME]`

Examples:
* `add assessment n/Test m/1` creates a new assessment that is tied to the 1st module shown when `list module` is run.

### Enrolling students

#### Enrolling students: `enrol`

Enrols 1 or more students to a class group.

Format: `enrol c/CLASS_GROUP_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS`

* Enrols the specified students to the class group at the specified `CLASS_GROUP_INDEX`.
* Students may be specified with either `all` (i.e. all students), `STUDENT_INDEXES` or `STUDENT_IDS`.
* Multiple `STUDENT_INDEXES` or `STUDENT_IDS` **should be separated with commas** (i.e. `s/1,2,3` or `s/e0123456,e0234567`).
* The index refers to the index number shown in the displayed student or class group list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `enrol c/1 s/all` enrols all students to the 1st class group shown when `list class` is run.
* `enrol c/1 s/1,2,3,4,5,6` enrols the 1st 6 students shown when `list student` is run to the 1st class group shown when `list class` is run.
* `enrol c/1 s/e0123456,e0234567` enrols the students with student IDs `E0123456` and `E0234567` to the 1st class group shown when `list class` is run.

#### Disenrolling students: `disenrol`

Disenrols 1 or more students from a class group.

Format: `disenrol c/CLASS_GROUP_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS`

* Disenrols the specified students from the class group at the specified `CLASS_GROUP_INDEX`.
* Students may be specified with either `all` (i.e. all students), `STUDENT_INDEXES` or `STUDENT_IDS`; they should already be enrolled in the specified class group.
* Multiple `STUDENT_INDEXES` or `STUDENT_IDS` **should be separated with commas** (i.e. `s/1,2,3` or `s/e0123456,e0234567`).
* The index refers to the index number shown in the displayed student or class group list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `disenrol c/1 s/all` disenrols all students from the 1st class group shown when `list class` is run.
* `disenrol c/1 s/1,2,3,4,5,6` disenrols the 1st 6 students belonging to the 1st class group shown when `list class` is run.
* `disenrol c/1 s/e0123456,e0234567` disenrols the students with student IDs `E0123456` and `E0234567` from the 1st class group shown when `list class` is run.

### Taking student attendance

#### Marking attendance: `mark`

Marks student(s)' attendance(s).

Format: `mark attend c/CLASS_GROUP_INDEX w/WEEK_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS`

* Marks the attendance(s) of the specified student(s) belonging to the class group at the specified `CLASS_GROUP_INDEX` for the specified week.
* Students may be specified with either `all` (i.e. all students), `STUDENT_INDEXES` or `STUDENT_IDS`.
* Multiple `STUDENT_INDEXES` or `STUDENT_IDS` **should be separated with commas** (i.e. `s/1,2,3` or `s/e0123456,e0234567`).
* The index refers to the index number shown in the displayed student or class group list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `mark attend c/1 w/3 s/all` marks the attendances of all the students belonging to the 1st class group for week 3.
* `mark attend c/1 w/3 s/1,2,3,4,5,6` marks the attendances of the 1st 6 students belonging to the 1st class group for week 3.
* `mark attend c/1 w/3 s/e0123456,e0234567` marks the attendances of the students with student IDs `E0123456` and `E0234567` belonging to the 1st class group for week 3.

#### Unmarking attendance: `unmark`

Unmarks student(s)' attendance(s).

Format: `unmark attend c/CLASS_GROUP_INDEX w/WEEK_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS`

* Unmarks the attendance(s) of the specified student(s) belonging to the class group at the specified `CLASS_GROUP_INDEX` for the specified week.
* Students may be specified with either `all` (i.e. all students), `STUDENT_INDEXES` or `STUDENT_IDS`.
* Multiple `STUDENT_INDEXES` or `STUDENT_IDS` **should be separated with commas** (i.e. `s/1,2,3` or `s/e0123456,e0234567`).
* The index refers to the index number shown in the displayed student or class group list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `unmark attend c/1 w/3 s/all` unmarks the attendances of all the students belonging to the 1st class group for week 3.
* `unmark attend c/1 w/3 s/1,2,3,4,5,6` unmarks the attendances of the 1st 6 students belonging to the 1st class group for week 3.
* `unmark attend c/1 w/3 s/e0123456,e0234567` unmarks the attendances of the students with student IDs `E0123456` and `E0234567` belonging to the 1st class group for week 3.

### Grading assessments: `grade`

Grades student's assessment.

Format: `grade {a/ASSESSMENT_INDEX | sn/SIMPLE_NAME m/MODULE_INDEX} s/all|STUDENT_INDEXES|STUDENT_IDS [g/GRADE]`

* The assessment can be specified with either the `ASSESSMENT_INDEX` or the `SIMPLE_NAME` and `MODULE_INDEX`.
* Students may be specified with either `all` (i.e. all students), `STUDENT_INDEXES` or `STUDENT_IDS`; they should already be enrolled in the module tied to the assessment.
* Multiple `STUDENT_INDEXES` or `STUDENT_IDS` **should be separated with commas** (i.e. `s/1,2,3` or `s/e0123456,e0234567`).
* The index refers to the index number shown in the displayed assessment or module list.
* The index **must be a positive integer** 1, 2, 3, …​
* If the grade is omitted, the value of the student's attempt will simply be incremented (i.e. `0` will be incremented to `1`).

Examples:
* `grade sn/lab1 m/1 s/all g/1` adds a grade of value `1` to the `lab1` assessment for all students enrolled in the 1st module shown when `list module` is run.
* `grade a/1 s/1,2,3,4,5,6` increments the grades of the 1st 6 students enrolled in the module tied to the 1st assessment shown when `list assessment` is run.
* `grade a/1 s/e0123456,e0234567 g/1` adds a grade of value `1` for the students with student IDs `E0123456` and `E0234567` to the 1st assessment shown when `list assessment` is run.

### Listing entries

#### Listing all students: `list student`

Shows a list of all students.

Format: `list student`

#### Listing all modules: `list module`

Shows a list of all modules.

Format: `list module`

#### Listing all class groups: `list class`

Shows a list of all class groups.

Format: `list class`

#### Listing all assessments: `list assessment`

Shows a list of all assessments.

Format: `list assessment`

#### Filtering entries

##### Filtering students

Shows a list of students belonging to either module or class group.

Format: `list student {m/MODULE_INDEX | c/CLASS_GROUP_INDEX}`

* Displays the students belonging to the module at the specified `MODULE_INDEX` or the class group at the specified `CLASS_GROUP_INDEX`.
* The index refers to the index number shown in the displayed module or class group list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list student m/1` displays the students belonging to the 1st module shown when `list module` is run.
* `list student c/2` displays the students belonging to the 2nd class group shown when `list class` is run.

##### Filtering class groups

Shows a list of class groups belonging to a module.

Format: `list class m/MODULE_INDEX`

* Displays the class groups belonging to the module at the specified `MODULE_INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list class m/1` displays the class groups belonging to the 1st module shown when `list module` is run.

##### Filtering assessments

Shows a list of all assessments belonging to a particular module.

Format: `list assessment m/MODULE_INDEX`

Examples:
* `list assessment m/1` displays the assessment(s) belonging to the 1st module shown when `list module` is run.

### Deleting entries

#### Deleting a student: `delete student`

Deletes the specified student from TAssist as well as the student's attempt(s) in the assessment(s).

Format: `delete student INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list student` followed by `delete student 2` deletes the 2nd student and their assessment(s)' attempt(s) in TAssist.

#### Deleting a module: `delete module`

Deletes the specified module from TAssist as well as its associated class group(s) and assessment(s).

Format: `delete module INDEX`

* Deletes the module at the specified `INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list module` followed by `delete module 2` deletes the 2nd module and its associated class group(s) and assessment(s) in TAssist.

#### Deleting a class group: `delete class`

Deletes the specified class group from TAssist.

Format: `delete class INDEX`

* Deletes the class group at the specified `INDEX`.
* The index refers to the index number shown in the displayed class groups list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list class` followed by `delete class 2` deletes the 2nd class group in TAssist.

#### Deleting an assessment: `delete assessment`

Deletes the specified assessment from TAssist.

Format: `delete assessment INDEX`

* Deletes the assessment at the specified `INDEX`.
* The index refers to the index number shown in the displayed class groups list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list assessment` followed by `delete assessment 2` deletes the 2nd assessment in TAssist.

### Clearing all entries: `clear`

Clears all entries from TAssist.

Format: `clear`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

TAssist data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAssist data are saved as a JSON file `[JAR file location]/data/tassist.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAssist will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAssist home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

<table>
<tbody>
    <tr>
        <th colspan="2">Action</th>
        <th>Format, Examples</th>
    </tr>
    <tr>
        <td rowspan="4">Add</td>
        <td>student</td>
        <td>
            <ul>
                <li>syntax: <code>add student id/STUDENT_ID n/NAME e/EMAIL [t/TELEGRAM_ID]</code></li>
                <li>e.g., <code>add student id/E0123456 n/John Doe e/johnd@example.com t/john_doe</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>module</td>
        <td>
            <ul>
                <li>syntax: <code>add module n/MODULE_NAME c/MODULE_CODE a/ACADEMIC_YEAR</code></li>
                <li>e.g., <code>add module n/Software Engineering Project c/CS2103T a/21S1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>class group</td>
        <td>
            <ul>
                <li>syntax: <code>add class id/CLASS_GROUP_ID t/CLASS_GROUP_TYPE m/MODULE_INDEX</code></li>
                <li>e.g., <code>add class id/T13 t/tutorial m/1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>assessment</td>
        <td>
            <ul>
                <li>syntax: <code>add assessment n/ASSESSMENT_NAME m/MODULE_INDEX [sn/SIMPLE_NAME]</code></li>
                <li>e.g., <code>add assessment n/Test m/1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="2">Enrolling</td>
        <td>student(s)</td>
        <td>
            <ul>
                <li>syntax: <code>enrol c/CLASS_GROUP_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS</code></li>
                <li>e.g., <code>enrol c/1 s/all</code></li>
                <li>e.g., <code>enrol c/1 s/1,2,3,4,5,6</code></li>
                <li>e.g., <code>enrol c/1 s/e0123456,e0234567</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>student(s)</td>
        <td>
            <ul>
                <li>syntax: <code>disenrol c/CLASS_GROUP_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS</code></li>
                <li>e.g., <code>disenrol c/1 s/all</code></li>
                <li>e.g., <code>disenrol c/1 s/1,2,3,4,5,6</code></li>
                <li>e.g., <code>disenrol c/1 s/e0123456,e0234567</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="1">Taking attendance</td>
        <td>student(s)</td>
        <td>
            <ul>
                <li>syntax: <code>mark attend c/CLASS_GROUP_INDEX w/WEEK_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS</code></li>
                <li>e.g., <code>mark attend c/1 w/3 s/all</code></li>
                <li>e.g., <code>mark attend c/1 w/3 s/1,2,3,4,5,6</code></li>
                <li>e.g., <code>mark attend c/1 w/3 s/e0123456,e0234567</code></li>
            </ul>
            <ul>
                <li>syntax: <code>unmark attend c/CLASS_GROUP_INDEX w/WEEK_INDEX s/all|STUDENT_INDEXES|STUDENT_IDS</code></li>
                <li>e.g., <code>unmark attend c/1 w/3 s/all</code></li>
                <li>e.g., <code>unmark attend c/1 w/3 s/1,2,3,4,5,6</code></li>
                <li>e.g., <code>unmark attend c/1 w/3 s/e0123456,e0234567</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="1">Grading</td>
        <td>assessment</td>
        <td>
            <ul>
                <li>syntax: <code>grade {a/ASSESSMENT_INDEX | sn/SIMPLE_NAME m/MODULE_INDEX} s/all|STUDENT_INDEXES|STUDENT_IDS [g/GRADE]</code></li>
                <li>e.g., <code>grade sn/lab1 m/1 s/all g/1</code></li>
                <li>e.g., <code>grade a/1 s/1,2,3,4,5,6</code></li>
                <li>e.g., <code>grade a/1 s/e0123456,e0234567 g/1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="4">List</td>
        <td>students</td>
        <td>
            <ul>
                <li>syntax: <code>list student</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>modules</td>
        <td>
            <ul>
                <li>syntax: <code>list module</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>class groups</td>
        <td>
            <ul>
                <li>syntax: <code>list class</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>assessments</td>
        <td>
            <ul>
                <li>syntax: <code>list assessment</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="3">Filter</td>
        <td>students</td>
        <td>
            <ul>
                <li>syntax: <code>list student {m/MODULE_INDEX | c/CLASS_GROUP_INDEX}</code></li>
                <li>e.g., <code>list student m/1</code></li>
                <li>e.g., <code>list student c/2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>class groups</td>
        <td>
            <ul>
                <li>syntax: <code>list class m/MODULE_INDEX</code></li>
                <li>e.g., <code>list class m/1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>assessments</td>
        <td>
            <ul>
                <li>syntax: <code>list assessment m/MODULE_INDEX</code></li>
                <li>e.g., <code>list assessment m/1</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="4">Delete</td>
        <td>student</td>
        <td>
            <ul>
                <li>syntax: <code>delete student INDEX</code></li>
                <li>e.g., <code>delete student 2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>module</td>
        <td>
            <ul>
                <li>syntax: <code>delete module INDEX</code></li>
                <li>e.g., <code>delete module 2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>class group</td>
        <td>
            <ul>
                <li>syntax: <code>delete class INDEX</code></li>
                <li>e.g., <code>delete class 2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>assessment</td>
        <td>
            <ul>
                <li>syntax: <code>delete assessment INDEX</code></li>
                <li>e.g., <code>delete assessment 2</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="1">Clearing</td>
        <td></td>
        <td>
            <ul>
                <li>syntax: <code>clear</code></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td rowspan="1">Exiting</td>
        <td></td>
        <td>
            <ul>
                <li>syntax: <code>exit</code></li>
            </ul>
        </td>
    </tr>
</tbody>
</table>
