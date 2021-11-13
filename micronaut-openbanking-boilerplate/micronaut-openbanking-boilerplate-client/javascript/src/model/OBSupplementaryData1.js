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
 * The OBSupplementaryData1 model module.
 * @module model/OBSupplementaryData1
 * @version 3.1.7
 */
export class OBSupplementaryData1 {
  /**
   * Constructs a new <code>OBSupplementaryData1</code>.
   * @alias module:model/OBSupplementaryData1
   * @class
   */
  constructor() {
  }

  /**
   * Constructs a <code>OBSupplementaryData1</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/OBSupplementaryData1} obj Optional instance to populate.
   * @return {module:model/OBSupplementaryData1} The populated <code>OBSupplementaryData1</code> instance.
   */
  static constructFromObject(data, obj) {
    if (data) {
      obj = obj || new OBSupplementaryData1();
      if (data.hasOwnProperty('id'))
        obj.id = ApiClient.convertToType(data['id'], 'String');
    }
    return obj;
  }
}

/**
 * @member {String} id
 */
OBSupplementaryData1.prototype.id = undefined;

