---
layout: page
title: Wen Xuan's Project Portfolio Page
---

### Project: TAssist

**TAssist** is a desktop app for Teaching Assistants to manage their students across different modules and keep track of students’ activities/participation in class. It is optimized for use via Command Line Interface (CLI). The user interacts with it using a CLI, and it has a GUI (Graphical User Interface) created with JavaFX. It is written in Java, and has about 11 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the model for student objects.
  * What it does: allows the TA to have the ability to manage students.
  * Justification: This feature improves the product significantly because a TA can interact with the student objects and the app should display the students accordingly.
  * Highlights: This enhancement affects existing commands (e.g. `add student`, `delete student`, `list student`). It required an in-depth analysis of design alternatives.


* **New Feature**: Added the ability to enrol student(s) into class groups.
  * What it does: allows the TA to enrol existing student(s) into an existing class group. This automatically links the student(s) to the module associated to the class group. The app then displays the existing students in the given class group.
  * Justification: This feature improves the product significantly because a TA can keep track of their students for each module they are teaching.
  * Highlights: This enhancement affects existing v1.3 commands (e.g. `mark`, `unmark`, `grade`, `disenrol`). It required an in-depth analysis of design alternatives. The implementation too was challenging as appropriate error messages were needed to handle different scenarios.


* **New Feature**: Added the ability to disenrol student(s) from class groups.
  * What it does: allows the TA to disenrol existing student(s) from an existing class group. This also removes the student(s) from the module associated to the class group, together with existing assessments done by the student(s). The app then displays the existing students in the given class group.
  * Justification: This feature improves the product significantly because a TA can disenrol student(s) which was previously enrolled to the class group.
  * Highlights: This enhancement affects existing v1.3 commands (e.g. `mark`, `unmark`, `grade`, `enrol`). It required an in-depth analysis of design alternatives. The implementation too was challenging as appropriate error messages were needed to handle different scenarios.


* **New Feature**: Added the ability to find students whose names contain any of the given keywords.
  * What it does: allows the TA to enter specific keywords to find existing student(s). Keywords which matches non-existing student(s) are accepted but nothing will be displayed by the app.
  * Justification: This feature improves the product because a TA can search for specific student(s) by their names.


* **New Feature**:
  * Wrote tests for the `Student` and `JsonAdaptedStudent` components.
  * Wrote tests for `FindCommand` as well as the `EnrolCommand` and `DisenrolCommand`.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=wxliong&breakdown=true)


* **Documentation**:
  * User Guide:
    * Added documentation for `Academic Year` under `add module`. [\#81](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/81)
    * Added `add` command. [\#21](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/21)
  * Developer Guide:
    * Added implementation details of the `add` and `enrol` feature. [\#113](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/113)
    * Added use cases for Student. [\#35](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/35)


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#142](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/142), [\#126](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/126), [\#19](), [\#42]()
  * Bug tested with team lead to ensure product is ready for PE-D: [\#156](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/156), [\#158](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/158), [\#163](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/163), [\#164](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/164)
  * Bug fixed with team lead to ensure product is ready for PE-D: [\#162](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/162), [\#165](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/165) 
  * Updated AB3 diagrams under `Design` to diagrams that is suitable for TAssist.
  * Added use cases for Student. [\#35](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/35)
