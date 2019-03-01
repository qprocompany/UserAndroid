package info.androidhive.recyclerviewsearch;

import android.content.res.AssetManager;
import android.util.Log;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ydenn on 8/1/2018.
 */

public class CallSoap {
    String str,base64String;

    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    //IP Public DB

    //Qpro DB
    public  final String SOAP_ADDRESS = "http://192.168.80.63/Sphaira_LIVE_ADT/Services/testandroid.asmx";
    //st.Maria
    //public  final String SOAP_ADDRESS = "http://36.91.120.14/SPHAIRA_TRAIN_ADT/Services/testandroid.asmx";
    //RSUD Palembang
    //public  final String SOAP_ADDRESS = "http://192.168.80.112/SPHAIRA_ADT/Services/testandroid.asmx";
    //public  final String SOAP_ADDRESS = "http://202.152.26.123/SPHAIRA_ADT/Services/testandroid.asmx";

    public final String SOAP_ACTION_JADWAL = "http://tempuri.org/JadwalDokter";
    public final String OPERATION_NAME_JADWAL = "JadwalDokter";
    public String JadwalHarian(String servunit)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_JADWAL);
        request.addProperty("servunit",servunit);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_JADWAL, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_RegisterApps = "http://tempuri.org/RegisterPatientApps";
    public  final String OPERATION_NAME_RegisterApps = "RegisterPatientApps";
    public String RegisterApps(String a,String b,String d,String e,String f, String g,String h)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_RegisterApps);
        request.addProperty("username",a);
        request.addProperty("password",b);
        request.addProperty("patientname",d);
        request.addProperty("sex",e);
        request.addProperty("dob",f);
        request.addProperty("medno",g);
        request.addProperty("phone",h);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_RegisterApps, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_loginapps = "http://tempuri.org/LoginPatientApps";
    public  final String OPERATION_NAME_loginapps = "LoginPatientApps";
    public String loginapps(String a, String b)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_loginapps);
        request.addProperty("username",a);
        request.addProperty("password",b);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_loginapps, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }
    public final String SOAP_ACTION_InfoUser = "http://tempuri.org/InfoUser";
    public  final String OPERATION_NAME_InfoUser = "InfoUser";
    public String InfoUser(String a)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_InfoUser);
        request.addProperty("username",a);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_InfoUser, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_Appointment = "http://tempuri.org/Appointment";
    public  final String OPERATION_NAME_Appointment = "Appointment";
    public String Appointment(String medicnom, String date, String name, String email, String work)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_Appointment);
        request.addProperty("medicnom",medicnom);
        request.addProperty("date",date);
        request.addProperty("name",name);
        request.addProperty("email",email);
        request.addProperty("workstation",work);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_Appointment, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }
    public final String SOAP_ACTION_DokterList = "http://tempuri.org/DokterByPoli";
    public  final String OPERATION_NAME_DokterList = "DokterByPoli";
    public String DokterByPoli(String servicecode)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_DokterList);
        request.addProperty("servunitid",servicecode);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_DokterList, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_Regis = "http://tempuri.org/Registration";
    public  final String OPERATION_NAME_Regis = "Registration";
    public String Registration(String medno,String serviceunit,String bussinessid,String paramid)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_Regis);
        request.addProperty("medno",medno);
        request.addProperty("servunit",serviceunit);
        request.addProperty("bussinesspartnerid",bussinessid);
        request.addProperty("paramid",paramid);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_Regis, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_DD = "http://tempuri.org/DetailDokter";
    public  final String OPERATION_NAME_DD = "DetailDokter";
    public String DokterDetail(String paraid)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_DD);
        request.addProperty("parid",paraid);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_DD, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_HistoryTomorrow = "http://tempuri.org/HistoryForTomorrow";
    public  final String OPERATION_NAME_HistoryTomorrow = "HistoryForTomorrow";
    public String HistoryTomorrow(String medicalno)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_HistoryTomorrow);
        request.addProperty("medicalno",medicalno);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_HistoryTomorrow, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_HistoryForToday = "http://tempuri.org/HistoryForToday";
    public  final String OPERATION_NAME_HistoryForToday = "HistoryForToday";
    public String HistoryForToday(String medicalno)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_HistoryForToday);
        request.addProperty("medicalno",medicalno);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_HistoryForToday,envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_History = "http://tempuri.org/History";
    public  final String OPERATION_NAME_History = "History";
    public String History(String medicalno)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_History);
        request.addProperty("medicnom",medicalno);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_History,envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_RegNo = "http://tempuri.org/RegistrationNo";
    public  final String OPERATION_NAME_RegNo = "RegistrationNo";
    public String RegNo(String medno)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_RegNo);
        request.addProperty("medno",medno);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_RegNo, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_AppointNo = "http://tempuri.org/AppointmentNo";
    public  final String OPERATION_NAME_AppointNo = "AppointmentNo";
    public String AppointNo(String medicnom)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_AppointNo);
        request.addProperty("medicnom",medicnom);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_AppointNo, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_Upload = "http://tempuri.org/UploadPictures";
    public  final String OPERATION_NAME_Upload = "UploadPictures";
    public String UploadImage(String medno,String base64)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_Upload);
        request.addProperty("medno",medno);
        request.addProperty("picture",base64);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_Upload, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_getimage = "http://tempuri.org/GetPictures";
    public  final String OPERATION_NAME_getimage = "GetPictures";
    public String getImage(String medno)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_getimage);
        request.addProperty("medno",medno);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_getimage, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_Promo = "http://tempuri.org/Promo";
    public  final String OPERATION_NAME_Promo = "Promo";
    public String Promo(String data1)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_Promo);
        request.addProperty("data",data1);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_Promo, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public final String SOAP_ACTION_Poli = "http://tempuri.org/PoliList";
    public  final String OPERATION_NAME_Poli = "PoliList";
    public String Poli(String data1)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME_Poli);
        request.addProperty("a",data1);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try
        {
            httpTransport.call(SOAP_ACTION_Poli, envelope);
            response = envelope.getResponse();
            SoapPrimitive response1 = (SoapPrimitive)envelope.getResponse();
            String data = response1.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }
}
