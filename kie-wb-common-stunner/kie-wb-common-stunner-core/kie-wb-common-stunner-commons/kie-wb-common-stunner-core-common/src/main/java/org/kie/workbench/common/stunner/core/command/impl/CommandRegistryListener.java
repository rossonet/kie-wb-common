/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.core.command.impl;

import org.kie.workbench.common.stunner.core.command.Command;
import org.kie.workbench.common.stunner.core.command.CommandListener;
import org.kie.workbench.common.stunner.core.command.CommandResult;
import org.kie.workbench.common.stunner.core.command.util.CommandUtils;
import org.kie.workbench.common.stunner.core.registry.command.CommandRegistry;

public abstract class CommandRegistryListener<C, V> implements CommandListener<C, V> {

    protected abstract CommandRegistry<Command<C, V>> getRegistry();

    @Override
    public void onExecute(final C context,
                          final Command<C, V> command,
                          final CommandResult<V> result) {
        if (!CommandUtils.isError(result)) {
            getRegistry().register(command);
        }
    }

    @Override
    public void onUndo(final C context,
                       final Command<C, V> command,
                       final CommandResult<V> result) {
        if (!CommandUtils.isError(result)) {
            getRegistry().pop();
        }
    }
}