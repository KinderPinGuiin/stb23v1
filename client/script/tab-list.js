/**
 * Class that allows you to handle multiple tabs on a page.
 */
class TabList {

  /**
   * A set of {tabName: tabElement} pair.
   */
  tabs = {};

  /**
   * Create a Tab instance with the given HTML element.
   * 
   * @param {Object} tabs A set of {tabName: tabElement} pair. 
   */
  constructor(tabs = {}) {
    this.tabs = tabs;
  }

  /**
   * Add the given tab to the tab list.
   * 
   * @param {string} tabName 
   * @param {HTMLElement} tabElement 
   */
  addTab(tabName, tabElement) {
    this.tabs[tabName] = tabElement;
  }

  /**
   * Return the tab list element and hide all the tab list elements except the
   * first one.
   * 
   * @returns {HTMLElement} The tab list.
   */
  getTabList() {
    // Create the tab list element
    const tabListElement = document.createElement("div");
    tabListElement.classList.add("tab-list");
    
    // Add the tab names events
    for (const key in this.tabs) {
      if (Object.hasOwnProperty.call(this.tabs, key)) {
        const element = this.tabs[key];

        const tabNameElement = document.createElement("span");
        tabNameElement.classList.add("tab-name");
        tabNameElement.innerText = key;
        tabListElement.appendChild(tabNameElement)

        tabNameElement.addEventListener("click", () => {
          this.hideAllElements();
          element.style.display = "block";
        });
      }
    }

    // Hide all the elements
    this.focus(0);

    return tabListElement;
  }

  /**
   * Hide all the given tab elements.
   */
  hideAllElements() {
    for (const key in this.tabs) {
      if (Object.hasOwnProperty.call(this.tabs, key)) {
        const element = this.tabs[key];
        element.style.display = "none";
      }
    }
  }

  /**
   * Focus on the nth tab.
   * 
   * @param {int} n The tab to focus.
   */
  focus(n) {
    let i = 0;
    for (const key in this.tabs) {
      if (Object.hasOwnProperty.call(this.tabs, key) && i != n) {
        const element = this.tabs[key];
        element.style.display = "none";
      } else if (i == n) {
        const element = this.tabs[key];
        element.style.display = "block";
      }
      ++i;
    }
  }
  
}