# Beneficiary.DefaultApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createBeneficiary**](DefaultApi.md#createBeneficiary) | **POST** /beneficiary | 
[**deleteBeneficiary**](DefaultApi.md#deleteBeneficiary) | **DELETE** /beneficiary{/BeneficiaryId} | 
[**getAccountBeneficiaries**](DefaultApi.md#getAccountBeneficiaries) | **GET** /account{/AccountId}/beneficiaries | 
[**getBeneficiaries**](DefaultApi.md#getBeneficiaries) | **GET** /beneficiaries | 
[**updateBeneficiary**](DefaultApi.md#updateBeneficiary) | **PUT** /beneficiary{/BeneficiaryId} | 

<a name="createBeneficiary"></a>
# **createBeneficiary**
> ModelObject createBeneficiary(body, opts)



### Example
```javascript
import {Beneficiary} from 'beneficiary';

let apiInstance = new Beneficiary.DefaultApi();
let body = new Beneficiary.OBBeneficiary5(); // OBBeneficiary5 | 
let opts = { 
  'topic': "topic_example", // String | 
  'key': "key_example" // String | 
};
apiInstance.createBeneficiary(body, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**OBBeneficiary5**](OBBeneficiary5.md)|  | 
 **topic** | **String**|  | [optional] 
 **key** | **String**|  | [optional] 

### Return type

[**ModelObject**](ModelObject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteBeneficiary"></a>
# **deleteBeneficiary**
> ModelObject deleteBeneficiary(beneficiaryId)



### Example
```javascript
import {Beneficiary} from 'beneficiary';

let apiInstance = new Beneficiary.DefaultApi();
let beneficiaryId = "beneficiaryId_example"; // String | 

apiInstance.deleteBeneficiary(beneficiaryId, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **beneficiaryId** | **String**|  | 

### Return type

[**ModelObject**](ModelObject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getAccountBeneficiaries"></a>
# **getAccountBeneficiaries**
> OBReadBeneficiary5 getAccountBeneficiaries(accountId)



### Example
```javascript
import {Beneficiary} from 'beneficiary';

let apiInstance = new Beneficiary.DefaultApi();
let accountId = "accountId_example"; // String | 

apiInstance.getAccountBeneficiaries(accountId, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | **String**|  | 

### Return type

[**OBReadBeneficiary5**](OBReadBeneficiary5.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getBeneficiaries"></a>
# **getBeneficiaries**
> OBReadBeneficiary5 getBeneficiaries(opts)



### Example
```javascript
import {Beneficiary} from 'beneficiary';

let apiInstance = new Beneficiary.DefaultApi();
let opts = { 
  'page': 56 // Number | 
};
apiInstance.getBeneficiaries(opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Number**|  | [optional] 

### Return type

[**OBReadBeneficiary5**](OBReadBeneficiary5.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateBeneficiary"></a>
# **updateBeneficiary**
> ModelObject updateBeneficiary(body, beneficiaryId, opts)



### Example
```javascript
import {Beneficiary} from 'beneficiary';

let apiInstance = new Beneficiary.DefaultApi();
let body = new Beneficiary.OBBeneficiary5(); // OBBeneficiary5 | 
let beneficiaryId = "beneficiaryId_example"; // String | 
let opts = { 
  'topic': "topic_example", // String | 
  'key': "key_example" // String | 
};
apiInstance.updateBeneficiary(body, beneficiaryId, opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**OBBeneficiary5**](OBBeneficiary5.md)|  | 
 **beneficiaryId** | **String**|  | 
 **topic** | **String**|  | [optional] 
 **key** | **String**|  | [optional] 

### Return type

[**ModelObject**](ModelObject.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

