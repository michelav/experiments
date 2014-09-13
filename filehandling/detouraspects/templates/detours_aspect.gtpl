package ${destpackage};

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
* Generated on ${date}
*/
public aspect ${aspectName} {
	pointcut OutputStreamWrite(byte[] theByte) : execution(void FileOutputStream.write(byte[])) &&
	within(${detpoint}) &&
    !within($aspectName) &&
	args(theByte);

	before(byte[] theByte) throws FileNotFoundException : OutputStreamWrite(theByte) {
		System.out.println("Capturing FileOutputStream.write method call");
	}
}
