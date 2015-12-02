package co.touchlab.researchstack.core.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.touchlab.researchstack.core.result.TaskResult;
import co.touchlab.researchstack.core.step.Step;

public class OrderedTask extends Task implements Serializable
{

    private List<Step> steps;

    public OrderedTask(String identifier, String scheduleId)
    {
        super(identifier, scheduleId);
        this.steps = new ArrayList<>();
    }

    public OrderedTask(String identifier, String scheduleId, List<Step> steps)
    {
        super(identifier, scheduleId);
        this.steps = new ArrayList<>(steps);
    }

    public OrderedTask(String identifier, String scheduleId, Step ... steps)
    {
        super(identifier, scheduleId);
        this.steps = Arrays.asList(steps);
    }


    @Override
    public Step getStepAfterStep(Step step, TaskResult result)
    {
        if(step == null)
        {
            return steps.get(0);
        }

        int nextIndex = steps.indexOf(step) + 1;

        if(nextIndex < steps.size())
        {
            return steps.get(nextIndex);
        }

        return null;
    }


    @Override
    public Step getStepBeforeStep(Step step, TaskResult result)
    {
        int nextIndex = steps.indexOf(step) - 1;

        if(nextIndex >= 0)
        {
            return steps.get(nextIndex);
        }

        return null;
    }

    @Override
    public Step getStepWithIdentifier(String identifier)
    {
        for (Step step : steps)
        {
            if (identifier.equals(step.getIdentifier()))
            {
                return step;
            }
        }
        return null;
    }

    @Override
    public TaskProgress getProgressOfCurrentStep(Step step, TaskResult result)
    {
        return null;
    }

    @Override
    public void validateParameters()
    {
    }

    public void addStep(Step step)
    {
        if (steps == null)
        {
            steps = new ArrayList<>();
        }

        steps.add(step);
    }

    public List<Step> getSteps()
    {
        return steps;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof OrderedTask)
        {
            OrderedTask orderedTask = (OrderedTask) o;
            return getIdentifier().equals(orderedTask.getIdentifier()) && steps.equals(
                    orderedTask.getSteps());
        }
        return false;
    }

    @Override
    public int getNumberOfSteps()
    {
        return steps.size();
    }
}