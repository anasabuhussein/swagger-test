/*
 * Beneficiary
 * The beneficiaries resource is used by an AISP to retrieve the account beneficiaries information for a specific AccountId or to retrieve the beneficiaries information in bulk for account(s) that the PSU has authorised to access.
 *
 * OpenAPI spec version: 3.1.7
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 *
 * Swagger Codegen version: 3.0.29
 *
 * Do not edit the class manually.
 *
 */
import {ApiClient} from '../ApiClient';

/**
 * Enum class OBAddressTypeCode.
 * @enum {String}
 * @readonly
 */
const OBAddressTypeCode = {
  /**
   * value: "HOME"
   * @const
   */
  HOME: "HOME",

  /**
   * value: "BUSINESS"
   * @const
   */
  BUSINESS: "BUSINESS",

  /**
   * value: "RECIPIENT"
   * @const
   */
  RECIPIENT: "RECIPIENT",

  /**
   * Returns a <code>OBAddressTypeCode</code> enum value from a JavaScript object name.
   * @param {Object} data The plain JavaScript object containing the name of the enum value.
  * @return {module:model/OBAddressTypeCode} The enum <code>OBAddressTypeCode</code> value.
   */
  constructFromObject: function(object) {
    return object;
  }
};

export {OBAddressTypeCode};