package com.example.abstractfactory;

public class DeviceDriver {
	public static void main(String[] args) {
	}
}

abstract class ResFactory {
	abstract public DisplayDriver getDispDrivr();
	abstract public PrinterDriver getPrtDrvr();
}

class LowResFact extends ResFactory {

	@Override
	public DisplayDriver getDispDrivr() {
		return new LRDD();
	}

	@Override
	public PrinterDriver getPrtDrvr() {
		return new LRPD();
	}
	
}
class HighResFact extends ResFactory {

	@Override
	public DisplayDriver getDispDrivr() {
		return new HRDD();
	}

	@Override
	public PrinterDriver getPrtDrvr() {
		return new HRPD();
	}
	
}

abstract class DisplayDriver {	
}
class LRDD extends DisplayDriver {
}
class HRDD extends DisplayDriver {
}

abstract class PrinterDriver {
}
class LRPD extends PrinterDriver {
}
class HRPD extends PrinterDriver {
}
