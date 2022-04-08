package seedu.address.model.classgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClassGroups.CS2040B10A;
import static seedu.address.testutil.TypicalClassGroups.CS2101G09;
import static seedu.address.testutil.TypicalLessons.LESSON2;
import static seedu.address.testutil.TypicalModules.CS2101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.classgroup.exceptions.ClassGroupNotFoundException;
import seedu.address.model.classgroup.exceptions.DuplicateClassGroupException;
import seedu.address.testutil.ClassGroupBuilder;

//@@author jxt00
public class UniqueClassGroupListTest {
    private final UniqueClassGroupList uniqueClassGroupList = new UniqueClassGroupList();

    @Test
    public void contains_nullClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.contains(null));
    }

    @Test
    public void contains_classGroupNotInList_returnsFalse() {
        assertFalse(uniqueClassGroupList.contains(CS2101G09));
    }

    @Test
    public void contains_classGroupInList_returnsTrue() {
        uniqueClassGroupList.add(CS2101G09);
        assertTrue(uniqueClassGroupList.contains(CS2101G09));
    }

    @Test
    public void contains_classGroupWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClassGroupList.add(CS2101G09);
        ClassGroup editedCS2101G09 = new ClassGroupBuilder(CS2101G09).build();
        assertTrue(uniqueClassGroupList.contains(editedCS2101G09));
    }

    @Test
    public void add_nullClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.add(null));
    }

    @Test
    public void add_duplicateClassGroup_throwsDuplicateClassGroupException() {
        uniqueClassGroupList.add(CS2101G09);
        assertThrows(DuplicateClassGroupException.class, () -> uniqueClassGroupList.add(CS2101G09));
    }

    @Test
    public void setClassGroup_nullTargetClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.setClassGroup(null, CS2101G09));
    }

    @Test
    public void setClassGroup_nullEditedClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.setClassGroup(CS2101G09, null));
    }

    @Test
    public void setClassGroup_targetClassGroupNotInList_throwsClassGroupNotFoundException() {
        assertThrows(ClassGroupNotFoundException.class, () -> uniqueClassGroupList.setClassGroup(CS2101G09, CS2101G09));
    }

    @Test
    public void setClassGroup_editedClassGroupIsSameClassGroup_success() {
        uniqueClassGroupList.add(CS2101G09);
        uniqueClassGroupList.setClassGroup(CS2101G09, CS2101G09);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2101G09);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroup_editedClassGroupHasSameIdentity_success() {
        uniqueClassGroupList.add(CS2101G09);
        ClassGroup editedAlice = new ClassGroupBuilder(CS2101G09).withLessons(LESSON2)
                .build();
        uniqueClassGroupList.setClassGroup(CS2101G09, editedAlice);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(editedAlice);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroup_editedClassGroupHasDifferentIdentity_success() {
        uniqueClassGroupList.add(CS2101G09);
        uniqueClassGroupList.setClassGroup(CS2101G09, CS2040B10A);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2040B10A);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroup_editedClassGroupHasNonUniqueIdentity_throwsDuplicateClassGroupException() {
        uniqueClassGroupList.add(CS2101G09);
        uniqueClassGroupList.add(CS2040B10A);
        assertThrows(
                DuplicateClassGroupException.class, () -> uniqueClassGroupList.setClassGroup(CS2101G09, CS2040B10A));
    }

    @Test
    public void remove_nullClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.remove(null));
    }

    @Test
    public void remove_classGroupDoesNotExist_throwsClassGroupNotFoundException() {
        assertThrows(ClassGroupNotFoundException.class, () -> uniqueClassGroupList.remove(CS2101G09));
    }

    @Test
    public void remove_existingClassGroup_removesClassGroup() {
        uniqueClassGroupList.add(CS2101G09);
        uniqueClassGroupList.remove(CS2101G09);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroups_nullUniqueClassGroupList_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> uniqueClassGroupList.setClassGroups((UniqueClassGroupList) null));
    }

    @Test
    public void setClassGroups_uniqueClassGroupList_replacesOwnListWithProvidedUniqueClassGroupList() {
        uniqueClassGroupList.add(CS2101G09);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2040B10A);
        uniqueClassGroupList.setClassGroups(expectedUniqueClassGroupList);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.setClassGroups((List<ClassGroup>) null));
    }

    @Test
    public void setClassGroups_list_replacesOwnListWithProvidedList() {
        uniqueClassGroupList.add(CS2101G09);
        List<ClassGroup> classGroupList = Collections.singletonList(CS2040B10A);
        uniqueClassGroupList.setClassGroups(classGroupList);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2040B10A);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroups_listWithDuplicateClassGroups_throwsDuplicateClassGroupException() {
        List<ClassGroup> listWithDuplicateClassGroups = Arrays.asList(CS2101G09, CS2101G09);
        assertThrows(
                DuplicateClassGroupException.class, (
                ) -> uniqueClassGroupList.setClassGroups(listWithDuplicateClassGroups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, (
                ) -> uniqueClassGroupList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void findClassesOfModule() {
        uniqueClassGroupList.add(CS2101G09);
        uniqueClassGroupList.add(CS2040B10A);
        assertTrue(uniqueClassGroupList.findClassesOfModule(CS2101).equals(new ArrayList<>(Arrays.asList(CS2101G09))));
    }
}
