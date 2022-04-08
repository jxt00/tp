---
layout: page
title: KangLi's Project Portfolio Page
---

### Project: TAssist

**TAssist** is a desktop app for Teaching Assistants to manage their students across different modules and keep track of students’ activities/participation in class. It is optimized for use via Command Line Interface (CLI). The user interacts with it using a CLI, and it has a GUI (Graphical User Interface) created with JavaFX. It is written in Java, and has about 11 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/#breakdown=true&search=lkldev)

* **New Feature**: Create model and storage classes for Modules
* **New Feature**: Create model and storage classes for TAssist
* **New Feature**: Create Parsers for enrol/disenrol, grade, list commands
* **New Feature**: Create model and add/delete/list commands for Assessment
* **New Feature**: Added Grade command
  * What it does: Allows TA to grade a student for their students for each of the assessment in the module.
  * Justification: This feature improves the product significantly as now the TA will be able to keep track of which student has submitted the assessments. They can use this command to easily grade the student's participation in class as well.
  * Highlights: To allow the TA to easily keep track of student participation, this command is created to increment the grade if no grade is provided so that the TA do not need to specify a grade for marking participation, they simply have to issue the command without a grade argument.

* **Enhancements to existing features**:
  * Update common classes (Model, Logic, ModelManager, LogicManager) to be compatible with TAssist.
  * Update Storage class to allow storing of TAssist data.
  * Improve storage in V1.3 to reduce storing of duplicated data.
  * Fix miscellaneous bugs before PE-D. [\#156](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/156), [\#158](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/158), [\#163](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/163), [\#164](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/164)

* **Documentation**:
  * User Guide:
    * Added documentation for project description and quick start. [\#4](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/4)
    * Fix miscellaneous errors in User Guide (e.g. making parameters optional, wrong parameters, etc)
  * Developer Guide:
    * Added use cases for modules
    * Added Grade command implementation
    * Update AB3 Design to match TAssist design
    * Fix miscellaneous errors in Developer Guide

* **Community**:
  * PRs reviewed (with non-trivial review comments):[\#79](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/79), [\#96](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/96), [\#112](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/112), [\#126](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/126), [\#134](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/134), [\#143](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/143), [\#155](https://github.com/AY2122S2-CS2103T-T13-2/tp/pull/143)
  * Contributed to forum discussions (examples: [\#21](https://github.com/nus-cs2103-AY2122S2/forum/issues/21), [\#41](https://github.com/nus-cs2103-AY2122S2/forum/issues/41), [\#43](https://github.com/nus-cs2103-AY2122S2/forum/issues/43), [\#87](https://github.com/nus-cs2103-AY2122S2/forum/issues/87), [\#128](https://github.com/nus-cs2103-AY2122S2/forum/issues/128), [\#170](https://github.com/nus-cs2103-AY2122S2/forum/issues/170), [\#237](https://github.com/nus-cs2103-AY2122S2/forum/issues/237))

* **Team Tasks**:
  * Setting up the GitHub team org/repo + codecov
  * General code enhancements: Renaming product, removing traces of AB3 from code and guides, updating common classes for team to move forward.
  * Release Management
  * Created sample data that will allow team to test their features to see if they are working as intended.
