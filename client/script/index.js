/*
 * Tabs handling 
 */

const tabList = new TabList({
  "API parameters": document.querySelector(".parameters-tab"),
  "GET requests": document.querySelector(".get-requests-tab"),
  "STB insertion": document.querySelector(".insertion-tab"),
  "STB deletion": document.querySelector(".deletion-tab"),
});

// Load the tab list and append it after the client title
document.querySelector("h1").after(tabList.getTabList());
tabList.focus(3);

/*
 * STB input handling
 */

// Bind the drag and drop events on the STB file input's label
DragAndDropFileInput.bindEvents(document.querySelector(".custom-file-input"), (files) => {
  // Only get the first file and add it to the STB file input
  if (files.length > 0) {
    const dataTransfer = new DataTransfer();
    dataTransfer.items.add(files[0]);
    const stbInput = document.querySelector("#stb-insert-input"); 
    stbInput.files = dataTransfer.files;
    stbInput.dispatchEvent(new Event("change"));
  }
});

// Bind a change event listener on the input to update the content text area
document.querySelector("#stb-insert-input").addEventListener("change", (e) => {
  if (e.target.files.length > 0) {
    const file = e.target.files[0];
    if (file.type == "text/xml") {
      // Read the file content and add it to the stb content textarea
      const reader = new FileReader();
      reader.readAsText(file);
      reader.addEventListener("load", (e) => {
        document.querySelector(".stb-content").value = e.target.result;
      });
    }
  }
});

/*
 * GET requests handling
 */

// Add an ID input if the selected request needs an ID
const getRequestSelector = document.querySelector(".get-request-selector select");
getRequestSelector.addEventListener("change", (e) => {
  if (e.target.selectedOptions[0].dataset.requireId !== undefined) {
    // Add the ID input
    if (document.querySelector("get-request-id-container") == undefined) {
      const idInputContainer = document.createElement("div");
      idInputContainer.classList.add("get-request-id-container");

      const idLabel = document.createElement("label");
      idLabel.innerText = "ID : ";
      idInputContainer.appendChild(idLabel);

      const idInput = document.createElement("input");
      idInput.type = "number";
      idInputContainer.appendChild(idInput);

      document.querySelector(".get-request-selector").after(idInputContainer);
    }
  } else {
    // Remove the ID input container
    const idInputContainer = document.querySelector(".get-request-id-container");
    if (idInputContainer != null) {
      idInputContainer.remove();
    }
  }
});

// Handle the GET requests submit
document.querySelector(".get-requests-tab button").addEventListener("click", () => {
  // Load the request data
  const selectedOption = getRequestSelector.selectedOptions[0];
  let endpoint = selectedOption.value;
  let stbId = null;
  if (document.querySelector(".get-request-id-container") != null) {
    stbId = parseInt(document.querySelector(".get-request-id-container input").value);
  }

  // Setup the request
  const params = stbId == null ? {} : {"id": stbId};

  // Send it
  STBApiRequester
    .get(endpoint, params)
    .then(async (response) => {
      // Clear the response area
      const responseContainer = document.querySelector(
        ".get-requests-tab .response-container"
      );
      if (responseContainer.querySelector("pre") != null) {
        responseContainer.querySelector("pre").remove();
      }
      if (responseContainer.querySelector(".html-content") != null) {
        responseContainer.querySelector(".html-content").remove();
      }

      // Check the response content type
      if (response.headers.get("Content-Type") == "application/xml") {
        // XML response
        // Parse the response
        const parsedResponse = new XmlBeautify().beautify(
          await response.text(),
          {
            indent: "  ", 
            useSelfClosingElement: true
          }
        );

        const responseArea = document.createElement("pre");
        responseArea.innerText = parsedResponse;
        responseContainer.appendChild(responseArea);
      } else if (response.headers.get("Content-Type").startsWith("text/html")) {
        // HTML response
        // Parse the response
        const htmlResponse = new DOMParser()
          .parseFromString(await response.text(), "text/html");
        
        // Insert the HTML
        const htmlContentElement = document.createElement("div");
        htmlContentElement.classList.add("html-content");
        htmlContentElement.innerHTML = htmlResponse
          .querySelector("body")
          .innerHTML;
        responseContainer.appendChild(htmlContentElement);
      }
    });
});

/*
 * STB Insertion
 */

// Send the request when the user click on the button
document.querySelector(".insertion-tab button").addEventListener("click", () => {
  // Clear the response container
  const responseContainer = document.querySelector(
    ".insertion-tab .response-container"
  );
  if (responseContainer.querySelector("pre") != null) {
    responseContainer.querySelector("pre").remove();
  }

  // Get the textarea content
  const stb = document.querySelector(".insertion-tab textarea").value;
  STBApiRequester.post("/stb23/insert", stb, {
    "Content-Type": "application/xml",
  }).then(async (response) => {
    // Parse the response
    const parsedResponse = new XmlBeautify().beautify(
      await response.text(),
      {
        indent: "  ", 
        useSelfClosingElement: true
      }
    );
    
    const responseArea = document.createElement("pre");
    responseArea.innerText = parsedResponse;
    responseContainer.appendChild(responseArea);
  });
});

/*
 * STB deletion
 */

// Send the request when the user click on the button
document.querySelector(".deletion-tab button").addEventListener("click", () => {
  // Clear the response container
  const responseContainer = document.querySelector(
    ".deletion-tab .response-container"
  );
  if (responseContainer.querySelector("pre") != null) {
    responseContainer.querySelector("pre").remove();
  }

  // Get the STB ID
  const stbId = parseInt(
    document.querySelector(".deletion-tab #delete-stb-id").value
  );
  STBApiRequester.delete("/stb23/delete/{id}", {"id": stbId})
    .then(async (response) => {
      // Parse the response
      const parsedResponse = new XmlBeautify().beautify(
        await response.text(),
        {
          indent: "  ",
          useSelfClosingElement: true
        }
      );
      
      const responseArea = document.createElement("pre");
      responseArea.innerText = parsedResponse;
      responseContainer.appendChild(responseArea);
    });
});

// Triggers a change event on page reload
document
  .querySelector(".get-request-selector select")
  .dispatchEvent(new Event("change"));
