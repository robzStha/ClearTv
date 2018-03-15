package com.app.cleartv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerErrorResponse {

@SerializedName("odata.error")
@Expose
private OdataError odataError;

public OdataError getOdataError() {
return odataError;
}

public void setOdataError(OdataError odataError) {
this.odataError = odataError;
}

}