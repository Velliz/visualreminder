package org.uiieditt.visualreminder.utility;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * This is a raw lib to upload file
 * Usage it on doInBackground if you using AsyncTask to perform multipartRequest method
 *
 * Usage Sample:
 * MultipartUpload x = new MultipartUpload();
 * MultipartUpload.FileObject p = x.new FileObject();
 * p.setFilepath(pathToVideoFile);
 * p.getFilefield("video");
 * p.getFileMimeType("video/mp4");
 * Map<String, MultipartUpload.FileObject> test = new HashMap<String, MultipartUpload.FileObject>();
 * test.put("file_key", p);
 *
 * Map<String, String> params = new HashMap<String, String>();
 * params.put("foo", hash);
 * params.put("bar", caption);
 * String result = x.multipartRequest(URL_UPLOAD_VIDEO, params, test);
 */
public class MultipartUpload {

    public class FileObject {

        String filepath;
        String filefield;
        String fileMimeType;

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public String getFilefield() {
            return filefield;
        }

        public void setFilefield(String filefield) {
            this.filefield = filefield;
        }

        public String getFileMimeType() {
            return fileMimeType;
        }

        public void setFileMimeType(String fileMimeType) {
            this.fileMimeType = fileMimeType;
        }
    }

    /**
     *
     * @param urlTo url destination
     * @param parmas post parameter
     * @param uploads array of file
     * @return String
     */
    public String multipartRequest(String urlTo, Map<String, String> parmas, Map<String, FileObject> uploads) throws Exception {
        HttpURLConnection connection;
        DataOutputStream outputStream;
        InputStream inputStream;

        String twoHyphens = "--";
        String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
        String lineEnd = "\r\n";

        String result;

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1024 * 1024;

        try {
            URL url = new URL(urlTo);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
            for (String key : uploads.keySet()) {
                FileObject fileObjects = uploads.get(key);

                File file = new File(fileObjects.filepath);
                FileInputStream fileInputStream = new FileInputStream(file);

                String[] q = fileObjects.filepath.split("/");
                int idx = q.length - 1;

                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + fileObjects.filefield + "\"; filename=\"" + q[idx] + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: " + fileObjects.fileMimeType + lineEnd);
                outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);

                //outputStream.writeBytes(lineEnd);

                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    outputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                outputStream.writeBytes(lineEnd);
                fileInputStream.close();
            }

            // Upload POST Data
            for (String key : parmas.keySet()) {
                String value = parmas.get(key);

                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: text/plain" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(value);
                outputStream.writeBytes(lineEnd);
            }

            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            if (200 != connection.getResponseCode()) {
                throw new Exception("Failed to upload code:" + connection.getResponseCode() + " " + connection.getResponseMessage());
            }

            inputStream = connection.getInputStream();

            result = this.convertStreamToString(inputStream);

            inputStream.close();
            outputStream.flush();
            outputStream.close();

            return result;
        } catch (Exception e) {
            Log.v("MULTIPART", e.getMessage());
            throw new Exception(e);
        }

    }

    private String convertStreamToString(@NonNull InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
