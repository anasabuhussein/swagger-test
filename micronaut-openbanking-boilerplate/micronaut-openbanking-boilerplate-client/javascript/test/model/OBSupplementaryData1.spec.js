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
(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD.
    define(['expect.js', '../../src/index'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    factory(require('expect.js'), require('../../src/index'));
  } else {
    // Browser globals (root is window)
    factory(root.expect, root.Beneficiary);
  }
}(this, function(expect, Beneficiary) {
  'use strict';

  var instance;

  describe('(package)', function() {
    describe('OBSupplementaryData1', function() {
      beforeEach(function() {
        instance = new Beneficiary.OBSupplementaryData1();
      });

      it('should create an instance of OBSupplementaryData1', function() {
        // TODO: update the code to test OBSupplementaryData1
        expect(instance).to.be.a(Beneficiary.OBSupplementaryData1);
      });

      it('should have the property id (base name: "id")', function() {
        // TODO: update the code to test the property id
        expect(instance).to.have.property('id');
        // expect(instance.id).to.be(expectedValueLiteral);
      });

    });
  });

}));
