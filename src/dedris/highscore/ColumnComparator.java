package dedris.highscore;

import java.util.Comparator;

/**
 * Vergleich der Punktzahl für die Sortierung des Highscore-Arrays
 * 
 * @author DSIW
 * 
 */
@SuppressWarnings("rawtypes")
public class ColumnComparator implements Comparator
{
  private int columnToSortOn;


  // contructor to set the column to sort on.
  ColumnComparator(int columnToSortOn)
  {
    this.columnToSortOn = columnToSortOn;
  }


  // Implement the abstract method which tells
  // how to order the two elements in the array.
  public int compare (Object o1, Object o2)
  {
    // cast the object args back to string arrays
    String[] row1 = (String[]) o1;
    String[] row2 = (String[]) o2;

    // compare the desired column values
    // return row1[columnToSortOn].compareTo(row2[columnToSortOn]);
    if (Integer.parseInt(row1[columnToSortOn]) < Integer
        .parseInt(row2[columnToSortOn])) return 1;
    if (Integer.parseInt(row1[columnToSortOn]) > Integer
        .parseInt(row2[columnToSortOn])) return -1;
    return 0;
  }
}
