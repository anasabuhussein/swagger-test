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
import {ApiClient} from './ApiClient';
import {CountryCode} from './model/CountryCode';
import {Mate} from './model/Mate';
import {ModelObject} from './model/ModelObject';
import {OBAddressTypeCode} from './model/OBAddressTypeCode';
import {OBBeneficiary5} from './model/OBBeneficiary5';
import {OBBeneficiaryType1Code} from './model/OBBeneficiaryType1Code';
import {OBBranchAndFinancialInstitutionIdentification6} from './model/OBBranchAndFinancialInstitutionIdentification6';
import {OBCashAccount5} from './model/OBCashAccount5';
import {OBExternalAccountIdentification4Code} from './model/OBExternalAccountIdentification4Code';
import {OBExternalFinancialInstitutionIdentification4Code} from './model/OBExternalFinancialInstitutionIdentification4Code';
import {OBPostalAddress6} from './model/OBPostalAddress6';
import {OBReadBeneficiary5} from './model/OBReadBeneficiary5';
import {OBReadDataBeneficiary5} from './model/OBReadDataBeneficiary5';
import {OBSupplementaryData1} from './model/OBSupplementaryData1';
import {DefaultApi} from './api/DefaultApi';

/**
* The_beneficiaries_resource_is_used_by_an_AISP_to_retrieve_the_account_beneficiaries_information_for_a_specific_AccountId_or_to_retrieve_the_beneficiaries_information_in_bulk_for_account_s_that_the_PSU_has_authorised_to_access_.<br>
* The <code>index</code> module provides access to constructors for all the classes which comprise the public API.
* <p>
* An AMD (recommended!) or CommonJS application will generally do something equivalent to the following:
* <pre>
* var Beneficiary = require('index'); // See note below*.
* var xxxSvc = new Beneficiary.XxxApi(); // Allocate the API class we're going to use.
* var yyyModel = new Beneficiary.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* <em>*NOTE: For a top-level AMD script, use require(['index'], function(){...})
* and put the application logic within the callback function.</em>
* </p>
* <p>
* A non-AMD browser application (discouraged) might do something like this:
* <pre>
* var xxxSvc = new Beneficiary.XxxApi(); // Allocate the API class we're going to use.
* var yyy = new Beneficiary.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* </p>
* @module index
* @version 3.1.7
*/
export {
    /**
     * The ApiClient constructor.
     * @property {module:ApiClient}
     */
    ApiClient,

    /**
     * The CountryCode model constructor.
     * @property {module:model/CountryCode}
     */
    CountryCode,

    /**
     * The Mate model constructor.
     * @property {module:model/Mate}
     */
    Mate,

    /**
     * The ModelObject model constructor.
     * @property {module:model/ModelObject}
     */
    ModelObject,

    /**
     * The OBAddressTypeCode model constructor.
     * @property {module:model/OBAddressTypeCode}
     */
    OBAddressTypeCode,

    /**
     * The OBBeneficiary5 model constructor.
     * @property {module:model/OBBeneficiary5}
     */
    OBBeneficiary5,

    /**
     * The OBBeneficiaryType1Code model constructor.
     * @property {module:model/OBBeneficiaryType1Code}
     */
    OBBeneficiaryType1Code,

    /**
     * The OBBranchAndFinancialInstitutionIdentification6 model constructor.
     * @property {module:model/OBBranchAndFinancialInstitutionIdentification6}
     */
    OBBranchAndFinancialInstitutionIdentification6,

    /**
     * The OBCashAccount5 model constructor.
     * @property {module:model/OBCashAccount5}
     */
    OBCashAccount5,

    /**
     * The OBExternalAccountIdentification4Code model constructor.
     * @property {module:model/OBExternalAccountIdentification4Code}
     */
    OBExternalAccountIdentification4Code,

    /**
     * The OBExternalFinancialInstitutionIdentification4Code model constructor.
     * @property {module:model/OBExternalFinancialInstitutionIdentification4Code}
     */
    OBExternalFinancialInstitutionIdentification4Code,

    /**
     * The OBPostalAddress6 model constructor.
     * @property {module:model/OBPostalAddress6}
     */
    OBPostalAddress6,

    /**
     * The OBReadBeneficiary5 model constructor.
     * @property {module:model/OBReadBeneficiary5}
     */
    OBReadBeneficiary5,

    /**
     * The OBReadDataBeneficiary5 model constructor.
     * @property {module:model/OBReadDataBeneficiary5}
     */
    OBReadDataBeneficiary5,

    /**
     * The OBSupplementaryData1 model constructor.
     * @property {module:model/OBSupplementaryData1}
     */
    OBSupplementaryData1,

    /**
    * The DefaultApi service constructor.
    * @property {module:api/DefaultApi}
    */
    DefaultApi
};
