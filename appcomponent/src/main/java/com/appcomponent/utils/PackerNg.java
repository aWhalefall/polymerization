//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appcomponent.utils;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PackerNg {
    private static final String TAG = PackerNg.class.getSimpleName();
    private static final String EMPTY_STRING = "";
    private static String sCachedMarket;
    private static final String USAGE_TEXT = "Usage: java -jar PackerNg-x.x.x.jar apkFile marketFile [outputDir] ";
    private static final String INTRO_TEXT = "\nAttention: if your app using Android gradle plugin 2.2.0 or later, be sure to install one of the generated Apks to device or emulator, to ensure the apk can be installed without errors. More details please go to github https://github.com/mcxiaoke/packer-ng-plugin .\n";

    public PackerNg() {
    }

    public static String getMarket(Object context) {
        return getMarket(context, "0150002");
    }

    public static synchronized String getMarket(Object context, String defaultValue) {
        if (sCachedMarket == null) {
            sCachedMarket = getMarketInternal(context, defaultValue).market;
        }

        return sCachedMarket;
    }

    public static PackerNg.MarketInfo getMarketInfo(Object context) {
        return getMarketInfo(context, "");
    }

    public static synchronized PackerNg.MarketInfo getMarketInfo(Object context, String defaultValue) {
        return getMarketInternal(context, defaultValue);
    }

    private static PackerNg.MarketInfo getMarketInternal(Object context, String defaultValue) {
        String market;
        Exception error;
        try {
            String e = PackerNg.Helper.getSourceDir(context);
            market = PackerNg.Helper.readZipComment(new File(e));
            error = null;
        } catch (Exception var5) {
            market = null;
            error = var5;
        }

        return new PackerNg.MarketInfo(market == null ? defaultValue : market, error);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            PackerNg.Helper.println("Usage: java -jar PackerNg-x.x.x.jar apkFile marketFile [outputDir] ");
            PackerNg.Helper.println("\nAttention: if your app using Android gradle plugin 2.2.0 or later, be sure to install one of the generated Apks to device or emulator, to ensure the apk can be installed without errors. More details please go to github https://github.com/mcxiaoke/packer-ng-plugin .\n");
            System.exit(1);
        }

        File apkFile = new File(args[0]);
        File marketFile = new File(args[1]);
        File outputDir = new File(args.length >= 3 ? args[2] : "apks");
        if (!apkFile.exists()) {
            PackerNg.Helper.printErr("Apk file \'" + apkFile.getAbsolutePath() + "\' is not exists or not readable.");
            PackerNg.Helper.println("Usage: java -jar PackerNg-x.x.x.jar apkFile marketFile [outputDir] ");
            System.exit(1);
        } else if (!marketFile.exists()) {
            PackerNg.Helper.printErr("Market file \'" + marketFile.getAbsolutePath() + "\' is not exists or not readable.");
            PackerNg.Helper.println("Usage: java -jar PackerNg-x.x.x.jar apkFile marketFile [outputDir] ");
            System.exit(1);
        } else {
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            PackerNg.Helper.println("Apk File: " + apkFile.getAbsolutePath());
            PackerNg.Helper.println("Market File: " + marketFile.getAbsolutePath());
            PackerNg.Helper.println("Output Dir: " + outputDir.getAbsolutePath());
            List markets = null;

            try {
                markets = PackerNg.Helper.parseMarkets(marketFile);
            } catch (IOException var12) {
                PackerNg.Helper.printErr("Market file parse failed.");
                System.exit(1);
            }

            if (markets != null && !markets.isEmpty()) {
                String baseName = PackerNg.Helper.getBaseName(apkFile.getName());
                String extName = PackerNg.Helper.getExtension(apkFile.getName());
                int processed = 0;

                try {
                    Iterator ex = markets.iterator();

                    while (ex.hasNext()) {
                        String market = (String) ex.next();
                        String apkName = baseName + "-" + market + "." + extName;
                        File destFile = new File(outputDir, apkName);
                        PackerNg.Helper.copyFile(apkFile, destFile);
                        PackerNg.Helper.writeZipComment(destFile, market);
                        if (PackerNg.Helper.verifyMarket(destFile, market)) {
                            ++processed;
                            PackerNg.Helper.println("Generating apk " + apkName);
                        } else {
                            destFile.delete();
                            PackerNg.Helper.printErr("Failed to generate " + apkName);
                        }
                    }

                    PackerNg.Helper.println("[Success] All " + processed + " apks saved to " + outputDir.getAbsolutePath());
                    PackerNg.Helper.println("\nAttention: if your app using Android gradle plugin 2.2.0 or later, be sure to install one of the generated Apks to device or emulator, to ensure the apk can be installed without errors. More details please go to github https://github.com/mcxiaoke/packer-ng-plugin .\n");
                } catch (PackerNg.MarketExistsException var13) {
                    PackerNg.Helper.printErr("Market info exists in \'" + apkFile + "\', please using a clean apk.");
                    System.exit(1);
                } catch (IOException var14) {
                    PackerNg.Helper.printErr("" + var14);
                    System.exit(1);
                }

            } else {
                PackerNg.Helper.printErr("No markets found.");
                System.exit(1);
            }
        }
    }

    public static class Helper {
        static final String UTF_8 = "UTF-8";
        static final int ZIP_COMMENT_MAX_LENGTH = 65535;
        static final int SHORT_LENGTH = 2;
        static final byte[] MAGIC = new byte[]{33, 90, 88, 75, 33};

        public Helper() {
        }

        private static String getSourceDir(Object context) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {
            Class contextClass = Class.forName("android.content.Context");
            Class applicationInfoClass = Class.forName("android.content.pm.ApplicationInfo");
            Method getApplicationInfoMethod = contextClass.getMethod("getApplicationInfo", new Class[0]);
            Object appInfo = getApplicationInfoMethod.invoke(context, new Object[0]);
            Field publicSourceDirField = applicationInfoClass.getField("publicSourceDir");
            String sourceDir = (String) publicSourceDirField.get(appInfo);
            if (sourceDir == null) {
                Field getPackageCodePathMethod = applicationInfoClass.getField("sourceDir");
                sourceDir = (String) getPackageCodePathMethod.get(appInfo);
            }

            if (sourceDir == null) {
                Method getPackageCodePathMethod1 = contextClass.getMethod("getPackageCodePath", new Class[0]);
                sourceDir = (String) getPackageCodePathMethod1.invoke(context, new Object[0]);
            }

            return sourceDir;
        }

        private static boolean isMagicMatched(byte[] buffer) {
            if (buffer.length != MAGIC.length) {
                return false;
            } else {
                for (int i = 0; i < MAGIC.length; ++i) {
                    if (buffer[i] != MAGIC[i]) {
                        return false;
                    }
                }

                return true;
            }
        }

        private static void writeBytes(byte[] data, DataOutput out) throws IOException {
            out.write(data);
        }

        private static void writeShort(int i, DataOutput out) throws IOException {
            ByteBuffer bb = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
            bb.putShort((short) i);
            out.write(bb.array());
        }

        private static short readShort(DataInput input) throws IOException {
            byte[] buf = new byte[2];
            input.readFully(buf);
            ByteBuffer bb = ByteBuffer.wrap(buf).order(ByteOrder.LITTLE_ENDIAN);
            return bb.getShort(0);
        }

        public static void writeZipComment(File file, String comment) throws IOException {
            if (hasZipCommentMagic(file)) {
                throw new PackerNg.MarketExistsException("Zip comment already exists, ignore.");
            } else {
                byte[] data = comment.getBytes("UTF-8");
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                raf.seek(file.length() - 2L);
                writeShort(data.length + 2 + MAGIC.length, raf);
                writeBytes(data, raf);
                writeShort(data.length, raf);
                writeBytes(MAGIC, raf);
                raf.close();
            }
        }

        public static boolean hasZipCommentMagic(File file) throws IOException {
            RandomAccessFile raf = null;

            boolean var5;
            try {
                raf = new RandomAccessFile(file, "r");
                long index = raf.length();
                byte[] buffer = new byte[MAGIC.length];
                index -= (long) MAGIC.length;
                raf.seek(index);
                raf.readFully(buffer);
                var5 = isMagicMatched(buffer);
            } finally {
                if (raf != null) {
                    raf.close();
                }

            }

            return var5;
        }

        public static String readZipComment(File file) throws IOException {
            RandomAccessFile raf = null;

            String var7;
            try {
                raf = new RandomAccessFile(file, "r");
                long index = raf.length();
                byte[] buffer = new byte[MAGIC.length];
                index -= (long) MAGIC.length;
                raf.seek(index);
                raf.readFully(buffer);
                if (!isMagicMatched(buffer)) {
                    throw new PackerNg.MarketNotFoundException("Zip comment magic bytes not found");
                }

                index -= 2L;
                raf.seek(index);
                short length = readShort(raf);
                if (length <= 0) {
                    throw new PackerNg.MarketNotFoundException("Zip comment content not found");
                }

                index -= (long) length;
                raf.seek(index);
                byte[] bytesComment = new byte[length];
                raf.readFully(bytesComment);
                var7 = new String(bytesComment, "UTF-8");
            } finally {
                if (raf != null) {
                    raf.close();
                }

            }

            return var7;
        }

        private static String readZipCommentMmp(File file) throws IOException {
            boolean mappedSize = true;
            long fz = file.length();
            RandomAccessFile raf = null;
            MappedByteBuffer map = null;

            String var10;
            try {
                raf = new RandomAccessFile(file, "r");
                map = raf.getChannel().map(MapMode.READ_ONLY, fz - 10240L, 10240L);
                map.order(ByteOrder.LITTLE_ENDIAN);
                short index = 10240;
                byte[] buffer = new byte[MAGIC.length];
                int index1 = index - MAGIC.length;
                map.position(index1);
                map.get(buffer);
                if (!isMagicMatched(buffer)) {
                    return null;
                }

                index1 -= 2;
                map.position(index1);
                short length = map.getShort();
                if (length <= 0) {
                    return null;
                }

                index1 -= length;
                map.position(index1);
                byte[] bytesComment = new byte[length];
                map.get(bytesComment);
                var10 = new String(bytesComment, "UTF-8");
            } finally {
                if (map != null) {
                    map.clear();
                }

                if (raf != null) {
                    raf.close();
                }

            }

            return var10;
        }

        public static void writeMarket(File file, String market) throws IOException {
            writeZipComment(file, market);
        }

        public static String readMarket(File file) throws IOException {
            return readZipComment(file);
        }

        public static boolean verifyMarket(File file, String market) throws IOException {
            return market.equals(readMarket(file));
        }

        public static void println(String msg) {
            System.out.println(msg);
        }

        public static void printErr(String msg) {
            System.err.println(msg);
        }

        public static List<String> parseMarkets(File file) throws IOException {
            ArrayList markets = new ArrayList();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;

            for (int lineNo = 1; (line = br.readLine()) != null; ++lineNo) {
                String[] parts = line.split("#");
                if (parts.length > 0) {
                    String market = parts[0].trim();
                    if (market.length() > 0) {
                        markets.add(market);
                    }
                }
            }

            br.close();
            fr.close();
            return markets;
        }

        public static void copyFile(File src, File dest) throws IOException {
            if (!dest.exists()) {
                dest.createNewFile();
            }

            FileChannel source = null;
            FileChannel destination = null;

            try {
                source = (new FileInputStream(src)).getChannel();
                destination = (new FileOutputStream(dest)).getChannel();
                destination.transferFrom(source, 0L, source.size());
            } finally {
                if (source != null) {
                    source.close();
                }

                if (destination != null) {
                    destination.close();
                }

            }

        }

        public static boolean deleteDir(File dir) {
            File[] files = dir.listFiles();
            if (files != null && files.length != 0) {
                File[] var2 = files;
                int var3 = files.length;

                for (int var4 = 0; var4 < var3; ++var4) {
                    File file = var2[var4];
                    if (file.isDirectory()) {
                        deleteDir(file);
                    } else {
                        file.delete();
                    }
                }

                return true;
            } else {
                return false;
            }
        }

        public static String getExtension(String fileName) {
            int dot = fileName.lastIndexOf(".");
            return dot > 0 ? fileName.substring(dot + 1) : null;
        }

        public static String getBaseName(String fileName) {
            int dot = fileName.lastIndexOf(".");
            return dot > 0 ? fileName.substring(0, dot) : fileName;
        }
    }

    public static class MarketNotFoundException extends IOException {
        public MarketNotFoundException() {
        }

        public MarketNotFoundException(String message) {
            super(message);
        }
    }

    public static class MarketExistsException extends IOException {
        public MarketExistsException() {
        }

        public MarketExistsException(String message) {
            super(message);
        }
    }

    public static final class MarketInfo {
        public final String market;
        public final Exception error;

        public MarketInfo(String market, Exception error) {
            this.market = market;
            this.error = error;
        }

        public String toString() {
            return "MarketInfo{market=\'" + this.market + '\'' + ", error=" + this.error + '}';
        }
    }
}
