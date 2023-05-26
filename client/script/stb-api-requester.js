/**
 * The STB API requester use the parameters defined in the parameter tab to
 * make HTTP requests to the STB API.
 */
class STBApiRequester {

  /**
   * Send a GET HTTP request on the given endpoint.
   *  
   * @param  {string}  endpoint The API endpoint.
   * @param  {string}  params   The request params : {paramName: value, ...}.
   * @param  {Object}  headers  The request headers : {header: value, ...}.
   * @return {Promise}          The response's promise. 
   */
  static get(endpoint, params = {}, headers = {}) {
    // Set the URL params
    for (const param in params) {
      endpoint = endpoint.replace(`{${param}}`, params[param]);
    }

    return fetch(STBApiRequester.getApiURI() + endpoint, {
      headers: headers
    });
  }

  /**
   * Send a POST HTTP request on the given endpoint.
   *  
   * @param  {string}  endpoint The API endpoint.
   * @param  {Object}  body     The request body.
   * @param  {Object}  headers  The request headers : {header: value, ...}.
   * @return {Promise}          The response's promise. 
   */
  static post(endpoint, body = "", headers = {}) {
    return fetch(STBApiRequester.getApiURI() + endpoint, {
      method: "POST",
      headers: headers,
      body: body,
    });
  }

  static delete(endpoint, params = {}, headers = {}) {
    // Set the URL params
    for (const param in params) {
      endpoint = endpoint.replace(`{${param}}`, params[param]);
    }

    return fetch(STBApiRequester.getApiURI() + endpoint, {
      method: "DELETE",
      headers: headers
    });
  }

  /**
   * @return {string} The STB API URI, based on the client parameters.
   */
  static getApiURI() {
    const host = STBApiRequester._getParam("api-host") ;
    const port = STBApiRequester._getParam("api-port");
    if (port != "") {
      return host + ":" + port;
    }

    return host;
  }

  /**
   * Get the value of the given param name (use the input ID).
   * 
   * @param  {string} paramName The param name.
   * @return {string}           The param value or null if the name is invalid.
   */
  static _getParam(paramName) {
    return document.querySelector(`.parameter-container #${paramName}`).value;
  }

}