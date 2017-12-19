package com.less.aspider.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apache lang on 2017/12/19.
 */

public class SerializationUtil {

    // Clone
    //-----------------------------------------------------------------------
    /**
     * <p>Deep clone an {@code Object} using serialization.</p>
     *
     * <p>This is many times slower than writing clone methods by hand
     * on all objects in your object graph. However, for complex object
     * graphs, or for those that don't support deep cloning this can
     * be a simple alternative implementation. Of course all the objects
     * must be {@code Serializable}.</p>
     *
     * @param <T> the type of the object involved
     * @param object  the {@code Serializable} object to clone
     * @return the cloned object
     * @throws SerializationException (runtimeException) if the serialization fails
     */
    public static <T extends Serializable> T clone(final T object) {
        if (object == null) {
            return null;
        }
        final byte[] objectData = serialize(object);
        final ByteArrayInputStream bais = new ByteArrayInputStream(objectData);

        try (ClassLoaderAwareObjectInputStream in = new ClassLoaderAwareObjectInputStream(bais,
                object.getClass().getClassLoader())) {
            /*
             * when we serialize and deserialize an object,
             * it is reasonable to assume the deserialized object
             * is of the same type as the original serialized object
             */
            @SuppressWarnings("unchecked") // see above
            final T readObject = (T) in.readObject();
            return readObject;

        } catch (final ClassNotFoundException ex) {
            throw new RuntimeException("ClassNotFoundException while reading cloned object data", ex);
        } catch (final IOException ex) {
            throw new RuntimeException("IOException while reading or closing cloned object data", ex);
        }
    }

    public static byte[] serialize(final Serializable obj) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        serialize(obj, baos);
        return baos.toByteArray();
    }

    public static void serialize(final Serializable obj, final OutputStream outputStream) {
        try (ObjectOutputStream out = new ObjectOutputStream(outputStream)){
            out.writeObject(obj);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * <p>Custom specialization of the standard JDK {@link ObjectInputStream}
     * that uses a custom  <code>ClassLoader</code> to resolve a class.
     * If the specified <code>ClassLoader</code> is not able to resolve the class,
     * the context classloader of the current thread will be used.
     * This way, the standard deserialization work also in web-application
     * containers and application servers, no matter in which of the
     * <code>ClassLoader</code> the particular class that encapsulates
     * serialization/deserialization lives. </p>
     *
     * <p>For more in-depth information about the problem for which this
     * class here is a workaround, see the JIRA issue LANG-626. </p>
     */
    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes =
                new HashMap<>();

        static {
            primitiveTypes.put("byte", byte.class);
            primitiveTypes.put("short", short.class);
            primitiveTypes.put("int", int.class);
            primitiveTypes.put("long", long.class);
            primitiveTypes.put("float", float.class);
            primitiveTypes.put("double", double.class);
            primitiveTypes.put("boolean", boolean.class);
            primitiveTypes.put("char", char.class);
            primitiveTypes.put("void", void.class);
        }

        private final ClassLoader classLoader;

        /**
         * Constructor.
         * @param in The <code>InputStream</code>.
         * @param classLoader classloader to use
         * @throws IOException if an I/O error occurs while reading stream header.
         * @see ObjectInputStream
         */
        ClassLoaderAwareObjectInputStream(final InputStream in, final ClassLoader classLoader) throws IOException {
            super(in);
            this.classLoader = classLoader;
        }

        /**
         * Overridden version that uses the parameterized <code>ClassLoader</code> or the <code>ClassLoader</code>
         * of the current <code>Thread</code> to resolve the class.
         * @param desc An instance of class <code>ObjectStreamClass</code>.
         * @return A <code>Class</code> object corresponding to <code>desc</code>.
         * @throws IOException Any of the usual Input/Output exceptions.
         * @throws ClassNotFoundException If class of a serialized object cannot be found.
         */
        @Override
        protected Class<?> resolveClass(final ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            final String name = desc.getName();
            try {
                return Class.forName(name, false, classLoader);
            } catch (final ClassNotFoundException ex) {
                try {
                    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                } catch (final ClassNotFoundException cnfe) {
                    final Class<?> cls = primitiveTypes.get(name);
                    if (cls != null) {
                        return cls;
                    }
                    throw cnfe;
                }
            }
        }
    }
}
