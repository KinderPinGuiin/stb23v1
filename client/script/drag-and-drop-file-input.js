/**
 * This class add all the events to handle drag and drop on a file input. 
 */
class DragAndDropFileInput {

  /**
   * Bind all the events to the given element to handle drag and drop.
   * 
   * @param {HTMLInputElement} element 
   * @param {callable}         onDrop The function to call on file drop. Takes
   *                                  the files in parameter.
   */
  static bindEvents(element, onDrop) {
    // Add the drag-enter class on drag enter
    element.addEventListener("dragenter", (e) => {
      e.preventDefault();
      element.classList.add("drag-enter");
    });

    // Remove the drag-enter class on drag leave
    element.addEventListener("dragleave", (e) => {
      e.preventDefault();
      element.classList.remove("drag-enter");
    })

    // Prevent the default event on drag over
    element.addEventListener("dragover", (e) => {
      e.preventDefault();
    });

    // Remove the drag-enter class on drop and call the onDrop callback
    element.addEventListener("drop", (e) => {
      e.preventDefault();
      element.classList.remove("drag-enter");
      onDrop(e.dataTransfer.files);
    });
  }

}
