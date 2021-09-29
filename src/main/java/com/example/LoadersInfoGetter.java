package com.example;

/**
 * Класс с примерами кода по получению информации о стандартных загрузчках. Вроде как их 3: 
 * bootstrap, extension loader и SystemLoader (загружает пользовательские классы).
 * Но их можно и самому дописать, главное, чтобы кастомные наследовали 1 из 3х.
 */
public class LoadersInfoGetter {
    
    /**
     * Метод получния информации о системной и платформенном(?) загрузчиках.
     * @see ClassLoader
     * @return - строка с информацией.
     */
    public String getClassLoadersInfo() {
        ClassLoader loader = null;
        String loaderName = "";
        String loaderParentsName = "";

        StringBuilder builder = new StringBuilder();
        
        loader = getClass().getClassLoader();
        loaderName = loader.getName() != null ? loader.getName() : "NoName loader";
        loaderParentsName = loader.getParent().getName() != null ? loader.getParent().getName() : "NoName parent loader";
        builder.append(String.format("\nApp.class class loader = %s, its parent=%s", loaderName, loaderParentsName));
        builder.append('\n');

        loader = ClassLoader.getSystemClassLoader();
        loaderName = loader.getName() != null ? loader.getName() : "NoName loader";
        loaderParentsName = loader.getParent().getName() != null ? loader.getParent().getName() : "NoName parent loader";
        builder.append(String.format("System class loader = %s, its parent=%s", 
                        loaderName, 
                        loaderParentsName
        ));
        builder.append('\n');

        loader = ClassLoader.getPlatformClassLoader();
        loaderName = loader.getName() != null ? loader.getName() : "NoName loader";
        //loaderParentsName = loader.getParent().getName() != null ? loader.getParent().getName() : "NoName parent loader";
        builder.append(String.format("Platform class loader = %s, its parent=%s", 
                        loaderName, 
                        "No parent"
        ));

        return builder.toString();
    }
    
    /**
     * Метод, выводящий иерархию загрузчкиков, начиная с некотрого: сначала с того, что загрузил класс, потом с системного
     * (скорее всего этот класс загрузил системный, но вдруг-вдруг), потом платформенный. 
     * !Кстати, ссылка на bootstrap == null, поэтому его мы не получим.
     * @return - строка с информацией.
     */
    public String getLoadersHierarchyInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("Hierarhy of class loaders from current class:\n");
        ClassLoader loader = getClass().getClassLoader();
        while (loader != null) {
            String name = loader.getName() != null ? loader.getName() : "NoName";
            ClassLoader parent = loader.getParent();
            String parentName = "NoName";
            if (parent != null)
                parentName = loader.getParent().getName() != null ? loader.getParent().getName() : "NoName";
            builder.append(String.format("name=%s, parent=%s", name, parentName));
            loader = loader.getParent();
        }

        builder.append("\nHierarhy of class loaders from system class loader\n");
        ClassLoader sysLoader = ClassLoader.getSystemClassLoader();
        while (sysLoader != null) {
            String name = sysLoader.getName() != null ? sysLoader.getName() : "NoName";
            ClassLoader parent = sysLoader.getParent();
            String parentName = "NoName";
            if (parent != null)
                parentName = sysLoader.getParent().getName() != null ? sysLoader.getParent().getName() : "NoName";
            builder.append(String.format("name=%s, parent=%s", name, parentName));
            sysLoader = sysLoader.getParent();
        }

        builder.append("\nHierarhy of class loaders from platform class loader\n");
        ClassLoader phatformload = ClassLoader.getPlatformClassLoader();
        while (phatformload != null) {
            String name = phatformload.getName() != null ? phatformload.getName() : "NoName";
            ClassLoader parent = phatformload.getParent();
            String parentName = "NoName";
            if (parent != null)
                parentName = phatformload.getParent().getName() != null ? phatformload.getParent().getName() : "NoName";
            builder.append(String.format("name=%s, parent=%s", name, parentName));
            phatformload = phatformload.getParent();
        }

        return builder.toString();
    }
}
