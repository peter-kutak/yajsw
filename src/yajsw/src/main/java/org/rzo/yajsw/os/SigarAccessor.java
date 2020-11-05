package org.rzo.yajsw.os;

import java.lang.reflect.Method;

public class SigarAccessor 
{
	private static SigarAccessor instance;
	private Object sigar;
	private Method getProcCpuMethod;
	private Method getProcMemMethod;
	private Method getProcFdMethod;
	private Method getPercentMethod;
	private Method getPageFaultsMethod;
	private Method getTotalMethod;

	// extended sigar jar may not be present -> use introspection
	public static SigarAccessor instance()
	{
		if (instance == null)
		try
		{
			SigarAccessor result = new SigarAccessor();
			result.createSigar();
			instance = result;
		}
		catch (Exception ex)
		{
			System.out.println("Warning: could not create sigar: "+ex.getMessage());
		}
		return instance;
	}
	public int getProcCpu(long pid) throws Exception
	{
		Object procCpu = getProcCpuMethod.invoke(sigar, pid);
		return ((Double)getPercentMethod.invoke(procCpu)).intValue();
	}
	public int getPageFaults(long pid) throws Exception 
	{
		Object procMem = getProcMemMethod.invoke(sigar, pid);
		return ((Long) getPageFaultsMethod.invoke(procMem)).intValue();
	}
	public int getProcFd(long pid) throws Exception 
	{
		Object procFd = getProcFdMethod.invoke(sigar, pid);
		return ((Long) getTotalMethod.invoke(procFd)).intValue();
	}
	
	private void createSigar() throws Exception
	{
			Class clazz = SigarAccessor
					.class
					.getClassLoader()
					.loadClass("org.gridkit.lab.sigar.SigarFactory");
			Method rc = clazz.getMethod("newSigar");
			sigar = rc.invoke(null);

			Class clazz1 = SigarAccessor
					.class
					.getClassLoader()
					.loadClass("org.hyperic.sigar.SigarProxy");
			
			getProcCpuMethod = clazz1.getMethod("getProcCpu", long.class);
			getProcMemMethod = clazz1.getMethod("getProcMem", long.class);
			getProcFdMethod = clazz1.getMethod("getProcFd", long.class);
			
			Class clazz2 = SigarAccessor
					.class
					.getClassLoader()
					.loadClass("org.hyperic.sigar.ProcCpu");
			getPercentMethod = clazz2.getMethod("getPercent");
			
			Class clazz3 = SigarAccessor
					.class
					.getClassLoader()
					.loadClass("org.hyperic.sigar.ProcMem");
			getPageFaultsMethod = clazz3.getMethod("getPageFaults");

			Class clazz4 = SigarAccessor
					.class
					.getClassLoader()
					.loadClass("org.hyperic.sigar.ProcFd");
			getTotalMethod = clazz4.getMethod("getTotal");

	}
	public static void main(String[] args) throws Exception {
		long pid = 14428L;
		SigarAccessor sigar = SigarAccessor.instance();
		System.out.println(sigar.getProcCpu(pid));
		System.out.println(sigar.getPageFaults(pid));
		System.out.println(sigar.getProcFd(pid));
	}
}

