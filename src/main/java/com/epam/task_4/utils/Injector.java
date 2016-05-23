package com.epam.task_4.utils;

import com.epam.task_4.cache.HashMapBasedCache;
import com.epam.task_4.cache.TreeMapBasedCache;
import com.epam.task_4.cache.annotation.Cache;
import com.epam.task_4.cache.annotation.InjectCache;
import com.epam.task_4.cache.exceptions.NoSuchCacheException;
import com.epam.task_4.consumers.CacheConsumerParent;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class to inject {@linkplain com.epam.task_4.cache.Cache
 * Cache} to a field via reflection API.
 */
public class Injector {

    private static final List<Class<?>> CACHE_CLASSES = findClassesImplementing(com.epam.task_4.cache.Cache.class, com.epam.task_4.cache.Cache.class.getPackage());
    private static final Class<? extends Annotation> INJECT_ANNOTATION_CLASS = InjectCache.class;
    private static final Class<? extends Annotation> CACHE_ANNOTATION_CLASS = Cache.class;

    /**
     * Private constructor to prevent creation of consumers.
     */
    private Injector() {
    }

    /**
     * Injects a {@linkplain com.epam.task_4.cache.Cache Cache}
     * into the consumer fields marked with {@linkplain InjectCache}
     * annotation.
     *
     * @param consumer object which needs a cache injection
     */
    @SuppressWarnings("unchecked")
    public static void inject(CacheConsumerParent consumer) {
        Class<?> clazz = consumer.getClass();
        HashMap<String, Class> classesAnnotation = getClassesAnnotation();
        try {
            while (clazz != Object.class) {
                final Field[] fields = clazz.getDeclaredFields();
                for (final Field field : fields) {
                    if (field.isAnnotationPresent(INJECT_ANNOTATION_CLASS)) {
                        String cacheName = ((InjectCache) field.getAnnotation(INJECT_ANNOTATION_CLASS)).cacheName();
                        Class cacheClass = classesAnnotation.get(cacheName);
                        if (cacheClass == null) {
                            throw new NoSuchCacheException(String.format("Cache class annotated with %s name is not found!", cacheName));
                        }
                        com.epam.task_4.cache.Cache cacheObj = (com.epam.task_4.cache.Cache) cacheClass.getMethod("getInstance").invoke(null);
                        //To avoid problems with private fields
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        field.set(consumer, cacheObj.getValues());
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.err.println("Something goes wrong with reflection");
        } catch (NoSuchCacheException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Finds classes, which are marked with {@linkplain Cache}
     * annotations, caches names.
     *
     * @return map represented entries of < Cache Name, Represented Class >
     */
    private static HashMap<String, Class> getClassesAnnotation() {
        HashMap<String, Class> classesAnnotation = new HashMap<>();
        for (Class clazz : CACHE_CLASSES) {
            if (clazz.isAnnotationPresent(CACHE_ANNOTATION_CLASS)) {
                String cacheName = ((Cache) clazz.getAnnotation(CACHE_ANNOTATION_CLASS)).name();
                classesAnnotation.put(cacheName, clazz);
            }
        }
        return classesAnnotation;
    }

    /**
     * Finds list of classes implementing interface.
     *
     * @param interfaceClass interface to be implemented by classes
     * @param fromPackage package to search classes
     * @return list of classes
     */
    private static List<Class<?>> findClassesImplementing(final Class<?> interfaceClass, final Package fromPackage) {

        final List<Class<?>> found = new ArrayList<>();
        try {
            final List<Class<?>> targets = getAllClassesFromPackage(fromPackage.getName());
            found.addAll(targets.stream().filter(aTarget -> !aTarget.equals(interfaceClass) && interfaceClass.isAssignableFrom(aTarget)).collect(Collectors.toList()));
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error reading package name.");
        }
        catch (IOException e) {
            System.err.println("Error reading classes in package.");
        }
        return found;
    }

    /**
     * Load all classes from package.
     *
     * @param packageName package in which classes is searched
     * @return list of found classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static List<Class<?>> getAllClassesFromPackage(final String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        String classExt = ".class";
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            File[] files = directory.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - classExt.length())));
                }
            }
        }
        return classes;
    }
}
