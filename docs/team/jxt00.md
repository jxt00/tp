---
layout: page
title: Jia Xin's Project Portfolio Page
---

### Project: TAssist

**TAssist** is a desktop app for Teaching Assistants to manage their students across different modules and keep track of students’ activities/participation in class. It is optimized for use via Command Line Interface (CLI). The user interacts with it using a CLI, and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 11 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the model for class groups, lessons and student attendances.
  * What it does: Allows the TA to manage the class groups, lessons as well as the students enrolled in the class groups.
  * Justification: This feature improves the product significantly because a TA needs to manage the class groups he/she is teaching as well as the weekly lessons and student attendances and the app should provide a convenient way to represent them.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation too was challenging as changes were made to accomodate the model classes and methods needed for each version iteration (e.g. added `Lesson` and `StudentAttendance` in v1.3).

* **New Feature**: Added parsing for the mark and unmark commands.
  * What it does: Allows the TA to execute the mark and unmark commands by parsing the input.
  * Justification: This feature improves the product significantly because a TA needs to be able to take student attendances and the app should provide a convenient way to parse and check the TA's input.
  * Highlights: This enhancement required the creation of new methods to accommodate for the parsing of the `s/` prefix (refer below).

* **New Feature**: Added parsing for the `s/` prefix.
  * What it does: Allows the TA to specify the students he/she would like to run the commands on.
  * Justification: This feature improves the product significantly because a TA needs to be able to specify which students he/she would like to make changes to and the app should provide a convenient way to parse and check the TA's input.
  * Highlights: This enhancement affects v1.3 commands (e.g. `mark`, `unmark`). It required an in-depth analysis of design alternatives. The implementation too was challenging as various inputs need to be handled with the appropriate error messages.

* **New Feature**:
  * Wrote tests for the `ClassGroup`, `Lesson` and `StudentAttendance` components as well as their relative storage classes `JsonAdaptedClassGroup`, `JsonAdaptedLesson` and `JsonAdaptedStudentAttendance`.
  * Wrote tests for the `DeleteCommand` and `DeleteCommandParser`.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jxt00&breakdown=true)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete`, `enrol`, `disenrol`, `filter`, `mark`, `unmark`, `grade` and `find`. [\#13](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/13), [\#112](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/112), [\#137](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/137)
  * Developer Guide:
    * Added use cases for the components `class group` and `assessment`. [\#31](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/31), [\#142](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/142)
    * Added use cases and user stories for the features `enrol`, `disenrol`, `filter`, `mark`, `unmark`, `grade` and `find`. [\#142](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/142)
    * Added implementation details of the `delete` feature. [\#110](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/110), [\#112](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/112)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#61](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/61), [\#96](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/96), [\#98](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/98), [\#106](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/106), [\#113](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/113), [\#124](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/124)
  * Maintaining the issue tracker
    * Added and assigned issues. [\#10](https://github.com/AY2122S2-CS2103T-T13-2/tp/issues/10), [\#28](https://github.com/AY2122S2-CS2103T-T13-2/tp/issues/28), [\#43](https://github.com/AY2122S2-CS2103T-T13-2/tp/issues/43), [\#46](https://github.com/AY2122S2-CS2103T-T13-2/tp/issues/46), [\#153](https://github.com/AY2122S2-CS2103T-T13-2/tp/issues/153), [\#219](https://github.com/AY2122S2-CS2103T-T13-2/tp/issues/219)
  * Updating user/developer docs that are not specific to a feature
    * Adapted model component and diagrams to TAssist in the developer guide. [\#110](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/110), [\#112](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/112)
    * Updated command summary table in the user guide. [\#112](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/112)
    * Added command parameter table to the user guide. [\#141](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/141)
