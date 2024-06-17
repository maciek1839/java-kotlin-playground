package com.showmeyourcode.playground.java.code.exercise.bst;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.showmeyourcode.playground.java.CommonTestUtil.parseRowOfInts;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeNoDuplicatesExerciseTest {

    @Test
    void shouldRunMainWithoutErrors() {
        assertDoesNotThrow(() -> BinarySearchTreeNoDuplicatesExercise.main(new String[]{}));
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10,10'",
            "'10,9,8,7,6,5,4,3,2,1,1'",
            "'2,4,1,3,3'",
            "'5,2,1,3,8,7,9,9'",
            "'10,5,15,3,7,7'",
            "'1,1'"
    })
    void shouldFindValue(String row) {
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        for (int v : valuesToInsert) {
            assertTrue(root.search(new NodeNoDuplicates(v)));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10,10',1",
            "'10,9,8,7,6,5,4,3,2,1,1',1",
            "'2,4,1,3,3',1",
            "'5,2,1,3,8,7,9,9',1",
            "'10,5,15,3,7,7',3",
            "'1,1', 1"
    })
    void shouldFindMinValue(String row, int min) {
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        assertEquals(min, root.findMin());
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10,10'",
            "'10,9,8,7,6,5,4,3,2,1,1'",
            "'2,4,1,3,3'",
            "'5,2,1,3,8,7,9,9'",
            "'10,5,15,3,7,7'",
            "'1,1'"
    })
    void shouldRemoveValue(String row) {
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);
        int[] valuesToRemove = IntStream.of(valuesToInsert).distinct().toArray();
        // remove all elements except the last one as it should remove completely the tree and produce a null value
        int[] valuesToRemoveWithoutLastElement = Arrays.copyOfRange(valuesToRemove, 0, valuesToRemove.length - 1);

        for (int j : valuesToRemoveWithoutLastElement) {
            var toRemove = new NodeNoDuplicates(j);
            assertTrue(root.search(toRemove));
            root = root.remove(toRemove);
            assertFalse(root.search(toRemove));
        }

        // verify that removing the last element produces a null as the root
        int theLastElement = valuesToRemove[valuesToRemove.length-1];
        assertTrue(root.search(new NodeNoDuplicates(theLastElement)));
        root = root.remove(new NodeNoDuplicates(theLastElement));
        assertNull(root);
    }

    @Test
    void shouldNotProduceErrorWhenRemovingNonExistentElement(){
        int[] valuesToInsert = parseRowOfInts("1,2,3,4,5");
        var root = makeTree(valuesToInsert);

        root = root.remove(new NodeNoDuplicates(10));
        for(int v: valuesToInsert){
            assertTrue(root.search(new NodeNoDuplicates(v)));
        }
        assertEquals(valuesToInsert.length, root.countNodes());
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10',1",
            "'10,9,8,7,6,5,4,3,2,1',1",
            "'2,4,1',2",
            "'5,2,1,3,8,7,9',4",
            "'10,5,15,3,7',3",
            "'1',1"
    })
    void shouldCorrectlyCountElements(String row, int leafNodesNo){
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        assertEquals(valuesToInsert.length, root.countNodes());
        assertEquals(leafNodesNo, root.countLeafNodes());
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10',9",
            "'10,9,8,7,6,5,4,3,2,1',9",
            "'2,4,1',1",
            "'5,2,1,3,8,7,9',2",
            "'10,5,15,3,7,1',3",
            "'1',0"
    })
    void shouldCalculateTreeHeight(String row, int expectedHeight){
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        assertEquals(expectedHeight, root.height());
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10','1,2,3,4,5,6,7,8,9,10'",
            "'10,9,8,7,6,5,4,3,2,1','10,9,8,7,6,5,4,3,2,1'",
            "'2,4,1','2,1,4'",
            "'5,2,1,3,8,7,9','5,2,8,1,3,7,9'",
            "'10,5,15,3,7,1','10,5,15,3,7,1'",
            "'1','1'",
            "'10,5,15', '10,5,15'",
            "'10,5,15,3,7,12,18', '10,5,15,3,7,12,18'",
            "'10,20,30,40,50,60', '10,20,30,40,50,60'",
            "'10,8,6,4,2', '10,8,6,4,2'"
    })
    void shouldReturnOrderAccordinglyToBFS(String row, String expectedOutput){
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        assertIterableEquals(
                parseListOfInts(expectedOutput),
                root.bfs()
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10','1,2,3,4,5,6,7,8,9,10'",
            "'10,9,8,7,6,5,4,3,2,1','10,9,8,7,6,5,4,3,2,1'",
            "'2,4,1','2,1,4'",
            "'5,2,1,3,8,7,9','5,2,1,3,8,7,9'",
            "'10,5,15,3,7,1','10,5,3,1,7,15'",
            "'1','1'",
            "'1,2,3,4,5','1,2,3,4,5'",
            "'10,5,15,3,7,12,18', '10,5,3,7,15,12,18'",
            "'10,20,30,40,50,60', '10,20,30,40,50,60'",
            "'10,8,6,4,2', '10,8,6,4,2'"
    })
    void shouldReturnOrderAccordinglyToDFSPreorder(String row, String expectedOutput){
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        assertIterableEquals(
                parseListOfInts(expectedOutput),
                root.dfsPreorder()
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10','1,2,3,4,5,6,7,8,9,10'",
            "'10,9,8,7,6,5,4,3,2,1','1,2,3,4,5,6,7,8,9,10'",
            "'2,4,1','1,2,4'",
            "'5,2,1,3,8,7,9','1,2,3,5,7,8,9'",
            "'10,5,15,3,7,1','1,3,5,7,10,15'",
            "'1','1'",
            "'1,2,3,4,5','1,2,3,4,5'",
            "'10,5,15,3,7,12,18', '3,5,7,10,12,15,18'",
            "'10,20,30,40,50,60', '10,20,30,40,50,60'",
            "'10,8,6,4,2', '2,4,6,8,10'"
    })
    void shouldReturnOrderAccordinglyToDFSInorder(String row, String expectedOutput){
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        assertIterableEquals(
                parseListOfInts(expectedOutput),
                root.dfsInorder()
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10','10,9,8,7,6,5,4,3,2,1'",
            "'10,9,8,7,6,5,4,3,2,1','1,2,3,4,5,6,7,8,9,10'",
            "'2,4,1','1,4,2'",
            "'5,2,1,3,8,7,9','1,3,2,7,9,8,5'",
            "'10,5,15,3,7,1','1,3,7,5,15,10'",
            "'1','1'",
            "'1,2,3,4,5','5,4,3,2,1'",
            "'10,5,15,3,7,12,18', '3,7,5,12,18,15,10'",
            "'10,20,30,40,50,60', '60,50,40,30,20,10'",
            "'10,8,6,4,2', '2,4,6,8,10'"
    })
    void shouldReturnOrderAccordinglyToDFSPostorder(String row, String expectedOutput){
        int[] valuesToInsert = parseRowOfInts(row);
        var root = makeTree(valuesToInsert);

        assertIterableEquals(
                parseListOfInts(expectedOutput),
                root.dfsPostorder()
        );
    }

    private static @NotNull List<Integer> parseListOfInts(String expectedOutput) {
        return Arrays.stream(expectedOutput.split(",")).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
    }

    private static @NotNull NodeNoDuplicates makeTree(int[] valuesToInsert) {
        var root = new NodeNoDuplicates(valuesToInsert[0]);
        for (int v : Arrays.copyOfRange(valuesToInsert, 1, valuesToInsert.length)) {
            root.insert(new NodeNoDuplicates(v));
        }
        return root;
    }
}
