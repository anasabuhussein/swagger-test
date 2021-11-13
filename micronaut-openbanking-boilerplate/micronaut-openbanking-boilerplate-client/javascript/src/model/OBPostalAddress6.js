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
import {CountryCode} from './CountryCode';
import {OBAddressTypeCode} from './OBAddressTypeCode';

/**
 * The OBPostalAddress6 model module.
 * @module model/OBPostalAddress6
 * @version 3.1.7
 */
export class OBPostalAddress6 {
  /**
   * Constructs a new <code>OBPostalAddress6</code>.
   * @alias module:model/OBPostalAddress6
   * @class
   */
  constructor() {
  }

  /**
   * Constructs a <code>OBPostalAddress6</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/OBPostalAddress6} obj Optional instance to populate.
   * @return {module:model/OBPostalAddress6} The populated <code>OBPostalAddress6</code> instance.
   */
  static constructFromObject(data, obj) {
    if (data) {
      obj = obj || new OBPostalAddress6();
      if (data.hasOwnProperty('addressLine'))
        obj.addressLine = ApiClient.convertToType(data['addressLine'], 'String');
      if (data.hasOwnProperty('addressType'))
        obj.addressType = OBAddressTypeCode.constructFromObject(data['addressType']);
      if (data.hasOwnProperty('buildingNumber'))
        obj.buildingNumber = ApiClient.convertToType(data['buildingNumber'], 'String');
      if (data.hasOwnProperty('country'))
        obj.country = CountryCode.constructFromObject(data['country']);
      if (data.hasOwnProperty('countrySubDivision'))
        obj.countrySubDivision = ApiClient.convertToType(data['countrySubDivision'], 'String');
      if (data.hasOwnProperty('department'))
        obj.department = ApiClient.convertToType(data['department'], 'String');
      if (data.hasOwnProperty('id'))
        obj.id = ApiClient.convertToType(data['id'], 'Number');
      if (data.hasOwnProperty('postCode'))
        obj.postCode = ApiClient.convertToType(data['postCode'], 'String');
      if (data.hasOwnProperty('streetName'))
        obj.streetName = ApiClient.convertToType(data['streetName'], 'String');
      if (data.hasOwnProperty('subDepartment'))
        obj.subDepartment = ApiClient.convertToType(data['subDepartment'], 'String');
      if (data.hasOwnProperty('townName'))
        obj.townName = ApiClient.convertToType(data['townName'], 'String');
    }
    return obj;
  }
}

/**
 * @member {String} addressLine
 */
OBPostalAddress6.prototype.addressLine = undefined;

/**
 * @member {module:model/OBAddressTypeCode} addressType
 */
OBPostalAddress6.prototype.addressType = undefined;

/**
 * @member {String} buildingNumber
 */
OBPostalAddress6.prototype.buildingNumber = undefined;

/**
 * @member {module:model/CountryCode} country
 */
OBPostalAddress6.prototype.country = undefined;

/**
 * @member {String} countrySubDivision
 */
OBPostalAddress6.prototype.countrySubDivision = undefined;

/**
 * @member {String} department
 */
OBPostalAddress6.prototype.department = undefined;

/**
 * @member {Number} id
 */
OBPostalAddress6.prototype.id = undefined;

/**
 * @member {String} postCode
 */
OBPostalAddress6.prototype.postCode = undefined;

/**
 * @member {String} streetName
 */
OBPostalAddress6.prototype.streetName = undefined;

/**
 * @member {String} subDepartment
 */
OBPostalAddress6.prototype.subDepartment = undefined;

/**
 * @member {String} townName
 */
OBPostalAddress6.prototype.townName = undefined;

