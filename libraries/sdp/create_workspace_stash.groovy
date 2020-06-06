/*
  Copyright © 2018 Booz Allen Hamilton. All Rights Reserved.
  This software package is licensed under the Booz Allen Public License. The license can be found in the License file or at http://boozallen.github.io/licenses/bapl
*/

import hudson.AbortException

@Validate({ !config.skip_initial_checkout }) // validate so this runs prior to other @Init steps
void call(context){
    node(img: "default-centos"){
        cleanWs()
        try{
            checkout scm
        }catch(AbortException ex) {
            println "scm var not present, skipping source code checkout" 
        }
        stash name: 'workspace', allowEmpty: true, useDefaultExcludes: false
    }
}
