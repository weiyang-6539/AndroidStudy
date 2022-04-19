// RemoteInterface.aidl
package com.example.aidl;

// Declare any non-default types here with import statements

interface RemoteInterface {
    void remotePrintInterface(String str);

    void remotePrintMap(in Map map);

    void remotePrintStringBuilder(CharSequence c);
}