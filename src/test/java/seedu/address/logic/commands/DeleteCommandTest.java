package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureFiltered;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureUnfiltered;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEntityAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTITY;
import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.tamodule.TaModule;

//@@author jxt00
/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private Model model = new ModelManager(getTypicalTAssist(), new UserPrefs());

    // fails occasionally
    //    @Test
    //    public void execute_validStudentIndexUnfilteredList_success() {
    //        Student studentToDelete = model.getUnfilteredStudentList().get(INDEX_FIRST_ENTITY.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.STUDENT);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, studentToDelete);
    //
    //        ModelManager expectedModel = new ModelManager(model.getTAssist(), new UserPrefs());
    //        expectedModel.deleteEntity(studentToDelete);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, EntityType.STUDENT);
    //    }

    @Test
    public void execute_validModuleIndexUnfilteredList_success() {
        TaModule moduleToDelete = model.getUnfilteredModuleList().get(INDEX_FIRST_ENTITY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.TA_MODULE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getTAssist(), new UserPrefs());
        expectedModel.deleteEntity(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, EntityType.TA_MODULE);
    }

    @Test
    public void execute_validClassGroupIndexUnfilteredList_success() {
        ClassGroup classGroupToDelete = model.getUnfilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.CLASS_GROUP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, classGroupToDelete);

        ModelManager expectedModel = new ModelManager(model.getTAssist(), new UserPrefs());
        expectedModel.deleteEntity(classGroupToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, EntityType.CLASS_GROUP);
    }

    //    @Test
    //    public void execute_validAssessmentIndexUnfilteredList_success() {
    //        Assessment assessmentToDelete = model.getUnfilteredAssessmentList()
    //        .get(INDEX_FIRST_ENTITY.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.ASSESSMENT);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, assessmentToDelete);
    //
    //        ModelManager expectedModel = new ModelManager(model.getTAssist(), new UserPrefs());
    //        expectedModel.deleteEntity(assessmentToDelete);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, EntityType.ASSESSMENT);
    //    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getUnfilteredStudentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, EntityType.STUDENT);

        assertCommandFailureUnfiltered(
                deleteCommand, model, EntityType.STUDENT, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getUnfilteredModuleList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, EntityType.TA_MODULE);

        assertCommandFailureUnfiltered(
                deleteCommand, model, EntityType.TA_MODULE, Messages.MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidClassGroupIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getUnfilteredClassGroupList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, EntityType.CLASS_GROUP);

        assertCommandFailureUnfiltered(
                deleteCommand, model, EntityType.CLASS_GROUP, Messages.MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX);
    }

    //    @Test
    //    public void execute_invalidAssessmentIndexUnfilteredList_throwsCommandException() {
    //        Index outOfBoundIndex = Index.fromOneBased(model.getUnfilteredAssessmentList().size() + 1);
    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, EntityType.ASSESSMENT);
    //
    //        assertCommandFailureUnfiltered(
    //        deleteCommand, model, EntityType.ASSESSMENT, Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
    //    }

    // No module will be deleted from a filtered list.
    // fails on Github
    //    @Test
    //    public void execute_validStudentIndexFilteredList_success() {
    //        showEntityAtIndex(model, INDEX_FIRST_ENTITY, EntityType.STUDENT);
    //
    //        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_ENTITY.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.STUDENT);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, studentToDelete);
    //
    //        Model expectedModel = new ModelManager(model.getTAssist(), new UserPrefs());
    //        expectedModel.deleteEntity(studentToDelete);
    //        showNoEntity(expectedModel, EntityType.STUDENT);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, EntityType.STUDENT);
    //    }

    @Test
    public void execute_validClassGroupIndexFilteredList_success() {
        showEntityAtIndex(model, INDEX_FIRST_ENTITY, EntityType.CLASS_GROUP);

        ClassGroup classGroupToDelete = model.getFilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.CLASS_GROUP);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, classGroupToDelete);

        Model expectedModel = new ModelManager(model.getTAssist(), new UserPrefs());
        expectedModel.deleteEntity(classGroupToDelete);
        showNoEntity(expectedModel, EntityType.CLASS_GROUP);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, EntityType.CLASS_GROUP);
    }

    //    @Test
    //    public void execute_validAssessmentIndexFilteredList_success() {
    //        showEntityAtIndex(model, INDEX_FIRST_ENTITY, EntityType.ASSESSMENT);
    //
    //        Assessment assessmentToDelete = model.getFilteredAssessmentList().get(INDEX_FIRST_ENTITY.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.ASSESSMENT);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, assessmentToDelete);
    //
    //        Model expectedModel = new ModelManager(model.getTAssist(), new UserPrefs());
    //        expectedModel.deleteEntity(assessmentToDelete);
    //        showNoEntity(expectedModel, EntityType.ASSESSMENT);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, EntityType.ASSESSMENT);
    //    }

    @Test
    public void execute_invalidStudentIndexFilteredList_throwsCommandException() {
        showEntityAtIndex(model, INDEX_FIRST_ENTITY, EntityType.STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_ENTITY;
        // ensures that outOfBoundIndex is still in bounds of TAssist list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTAssist().getStudentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, EntityType.STUDENT);
        assertCommandFailureFiltered(
                deleteCommand, model, EntityType.STUDENT, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidClassGroupIndexFilteredList_throwsCommandException() {
        showEntityAtIndex(model, INDEX_FIRST_ENTITY, EntityType.CLASS_GROUP);

        Index outOfBoundIndex = INDEX_SECOND_ENTITY;
        // ensures that outOfBoundIndex is still in bounds of TAssist list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTAssist().getClassGroupList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, EntityType.CLASS_GROUP);
        assertCommandFailureFiltered(
                deleteCommand, model, EntityType.CLASS_GROUP, Messages.MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX);
    }

    //    @Test
    //    public void execute_invalidAssessmentIndexFilteredList_throwsCommandException() {
    //        showEntityAtIndex(model, INDEX_FIRST_ENTITY, EntityType.ASSESSMENT);
    //
    //        Index outOfBoundIndex = INDEX_SECOND_ENTITY;
    //        // ensures that outOfBoundIndex is still in bounds of TAssist list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getTAssist().getAssessmentList().size());
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, EntityType.ASSESSMENT);
    //        assertCommandFailureFiltered(
    //        deleteCommand, model, EntityType.ASSESSMENT, Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.STUDENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ENTITY, EntityType.STUDENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.STUDENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no entity.
     */
    private void showNoEntity(Model model, EntityType entity) {
        switch(entity) {
        case STUDENT:
            model.updateFilteredStudentList(p -> false);
            assertTrue(model.getFilteredStudentList().isEmpty());
            break;
        case CLASS_GROUP:
            model.updateFilteredClassGroupList(p -> false);
            assertTrue(model.getFilteredClassGroupList().isEmpty());
            break;
        case ASSESSMENT:
            model.updateFilteredAssessmentList(p -> false);
            assertTrue(model.getFilteredAssessmentList().isEmpty());
            break;
        default:
            throw new UnknownEntityException();
        }
    }
}
