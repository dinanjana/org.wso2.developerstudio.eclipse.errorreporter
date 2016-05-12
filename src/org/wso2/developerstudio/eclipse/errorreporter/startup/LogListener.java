/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.developerstudio.eclipse.errorreporter.startup;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.wso2.developerstudio.eclipse.errorreporter.other.ErrorReporter;

public class LogListener implements ILogListener {

	IStatus loggedStatus;

	@Override
	public void logging(IStatus status, String plugin) {

		this.loggedStatus = status;

		// method to check whether the error belongs to dev studio
		// here the dev studio plugin name shoud be included
		if (status.getSeverity() == IStatus.ERROR && plugin.equals("org.eclipse.core.runtime")) {
			System.out.println("testing-" + loggedStatus.getMessage());
			ErrorReporter errReporter = new ErrorReporter(status, plugin);
			//errReporter.openErrorDialog();
			errReporter.collectErrorInfo();
		}

	}

}
