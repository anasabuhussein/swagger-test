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
    describe('OBPostalAddress6', function() {
      beforeEach(function() {
        instance = new Beneficiary.OBPostalAddress6();
      });

      it('should create an instance of OBPostalAddress6', function() {
        // TODO: update the code to test OBPostalAddress6
        expect(instance).to.be.a(Beneficiary.OBPostalAddress6);
      });

      it('should have the property addressLine (base name: "addressLine")', function() {
        // TODO: update the code to test the property addressLine
        expect(instance).to.have.property('addressLine');
        // expect(instance.addressLine).to.be(expectedValueLiteral);
      });

      it('should have the property addressType (base name: "addressType")', function() {
        // TODO: update the code to test the property addressType
        expect(instance).to.have.property('addressType');
        // expect(instance.addressType).to.be(expectedValueLiteral);
      });

      it('should have the property buildingNumber (base name: "buildingNumber")', function() {
        // TODO: update the code to test the property buildingNumber
        expect(instance).to.have.property('buildingNumber');
        // expect(instance.buildingNumber).to.be(expectedValueLiteral);
      });

      it('should have the property country (base name: "country")', function() {
        // TODO: update the code to test the property country
        expect(instance).to.have.property('country');
        // expect(instance.country).to.be(expectedValueLiteral);
      });

      it('should have the property countrySubDivision (base name: "countrySubDivision")', function() {
        // TODO: update the code to test the property countrySubDivision
        expect(instance).to.have.property('countrySubDivision');
        // expect(instance.countrySubDivision).to.be(expectedValueLiteral);
      });

      it('should have the property department (base name: "department")', function() {
        // TODO: update the code to test the property department
        expect(instance).to.have.property('department');
        // expect(instance.department).to.be(expectedValueLiteral);
      });

      it('should have the property id (base name: "id")', function() {
        // TODO: update the code to test the property id
        expect(instance).to.have.property('id');
        // expect(instance.id).to.be(expectedValueLiteral);
      });

      it('should have the property postCode (base name: "postCode")', function() {
        // TODO: update the code to test the property postCode
        expect(instance).to.have.property('postCode');
        // expect(instance.postCode).to.be(expectedValueLiteral);
      });

      it('should have the property streetName (base name: "streetName")', function() {
        // TODO: update the code to test the property streetName
        expect(instance).to.have.property('streetName');
        // expect(instance.streetName).to.be(expectedValueLiteral);
      });

      it('should have the property subDepartment (base name: "subDepartment")', function() {
        // TODO: update the code to test the property subDepartment
        expect(instance).to.have.property('subDepartment');
        // expect(instance.subDepartment).to.be(expectedValueLiteral);
      });

      it('should have the property townName (base name: "townName")', function() {
        // TODO: update the code to test the property townName
        expect(instance).to.have.property('townName');
        // expect(instance.townName).to.be(expectedValueLiteral);
      });

    });
  });

}));
