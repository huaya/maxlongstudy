package com.maxlong.study.quartzZookeeper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;
import org.springframework.scheduling.quartz.JobMethodInvocationFailedException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.MethodInvoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/25 17:20
 *          类说明
 */
public class JobDetailProxyBean  extends ArgumentConvertingMethodInvoker
        implements FactoryBean<JobDetail>, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean, Job {

    private String key;

    private String name;

    private String group = Scheduler.DEFAULT_GROUP;

    private String description;

    private String Mode;

    private boolean concurrent = true;

    private String targetBeanName;

    private String beanName;

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private BeanFactory beanFactory;

    private JobDetail jobDetail;


    /**
     * Set the name of the job.
     * <p>Default is the bean name of this FactoryBean.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the group of the job.
     * <p>Default is the default group of the Scheduler.
     * @see org.quartz.Scheduler#DEFAULT_GROUP
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Specify whether or not multiple jobs should be run in a concurrent fashion.
     * The behavior when one does not want concurrent jobs to be executed is
     * realized through adding the {@code @PersistJobDataAfterExecution} and
     * {@code @DisallowConcurrentExecution} markers.
     * More information on stateful versus stateless jobs can be found
     * <a href="http://www.quartz-scheduler.org/documentation/quartz-2.1.x/tutorials/tutorial-lesson-03">here</a>.
     * <p>The default setting is to run jobs concurrently.
     */
    public void setConcurrent(boolean concurrent) {
        this.concurrent = concurrent;
    }

    /**
     * Set the name of the target bean in the Spring BeanFactory.
     * <p>This is an alternative to specifying {@link #setTargetObject "targetObject"},
     * allowing for non-singleton beans to be invoked. Note that specified
     * "targetObject" and {@link #setTargetClass "targetClass"} values will
     * override the corresponding effect of this "targetBeanName" setting
     * (i.e. statically pre-define the bean type or even the bean object).
     */
    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String getGroup() {
        return group;
    }

    @Override
    protected Class<?> resolveClassName(String className) throws ClassNotFoundException {
        return ClassUtils.forName(className, this.beanClassLoader);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {
        prepare();

        // Use specific name if given, else fall back to bean name.
        String name = (this.name != null ? this.name : this.beanName);

        // Consider the concurrent flag to choose between stateful and stateless job.
        Class<?> jobClass = (this.concurrent ? MethodInvokingJobDetailFactoryBean.MethodInvokingJob.class : MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob.class);

        // Build JobDetail instance.
        JobDetailImpl jdi = new JobDetailImpl();
        jdi.setName(name);
        jdi.setGroup(this.group);
        jdi.setJobClass((Class) jobClass);
        jdi.setDurability(true);
        jdi.getJobDataMap().put("methodInvoker", this);
        this.jobDetail = jdi;

        postProcessJobDetail(this.jobDetail);
    }

    /**
     * Callback for post-processing the JobDetail to be exposed by this FactoryBean.
     * <p>The default implementation is empty. Can be overridden in subclasses.
     * @param jobDetail the JobDetail prepared by this FactoryBean
     */
    protected void postProcessJobDetail(JobDetail jobDetail) {
    }


    /**
     * Overridden to support the {@link #setTargetBeanName "targetBeanName"} feature.
     */
    @Override
    public Class<?> getTargetClass() {
        Class<?> targetClass = super.getTargetClass();
        if (targetClass == null && this.targetBeanName != null) {
            Assert.state(this.beanFactory != null, "BeanFactory must be set when using 'targetBeanName'");
            targetClass = this.beanFactory.getType(this.targetBeanName);
        }
        return targetClass;
    }

    /**
     * Overridden to support the {@link #setTargetBeanName "targetBeanName"} feature.
     */
    @Override
    public Object getTargetObject() {
        Object targetObject = super.getTargetObject();
        if (targetObject == null && this.targetBeanName != null) {
            Assert.state(this.beanFactory != null, "BeanFactory must be set when using 'targetBeanName'");
            targetObject = this.beanFactory.getBean(this.targetBeanName);
        }
        return targetObject;
    }


    @Override
    public JobDetail getObject() {
        return this.jobDetail;
    }

    @Override
    public Class<? extends JobDetail> getObjectType() {
        return (this.jobDetail != null ? this.jobDetail.getClass() : JobDetail.class);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
    }

    public String getKey() {
        return key;
    }


    /**
     * Quartz Job implementation that invokes a specified method.
     * Automatically applied by MethodInvokingJobDetailFactoryBean.
     */
    public static class MethodInvokingJob extends QuartzJobBean {

        protected static final Log logger = LogFactory.getLog(MethodInvokingJobDetailFactoryBean.MethodInvokingJob.class);

        private MethodInvoker methodInvoker;

        /**
         * Set the MethodInvoker to use.
         */
        public void setMethodInvoker(MethodInvoker methodInvoker) {
            this.methodInvoker = methodInvoker;
        }

        /**
         * Invoke the method via the MethodInvoker.
         */
        @Override
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            try {
                context.setResult(this.methodInvoker.invoke());
            }
            catch (InvocationTargetException ex) {
                if (ex.getTargetException() instanceof JobExecutionException) {
                    // -> JobExecutionException, to be logged at info level by Quartz
                    throw (JobExecutionException) ex.getTargetException();
                }
                else {
                    // -> "unhandled exception", to be logged at error level by Quartz
                    throw new JobMethodInvocationFailedException(this.methodInvoker, ex.getTargetException());
                }
            }
            catch (Exception ex) {
                // -> "unhandled exception", to be logged at error level by Quartz
                throw new JobMethodInvocationFailedException(this.methodInvoker, ex);
            }
        }
    }


    public void setKey(String key) {
        this.key = key;
    }
}
