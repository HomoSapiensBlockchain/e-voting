/******************************************************************************
 * e-voting system                                                            *
 * Copyright (C) 2016 DSX Technologies Limited.                               *
 * *
 * This program is free software; you can redistribute it and/or modify       *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation; either version 2 of the License, or          *
 * (at your option) any later version.                                        *
 * *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied                         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * *
 * You can find copy of the GNU General Public License in LICENSE.txt file    *
 * at the top-level directory of this distribution.                           *
 * *
 * Removal or modification of this copyright notice is prohibited.            *
 * *
 ******************************************************************************/

package uk.dsxt.voting.client.datamodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import uk.dsxt.voting.common.domain.dataModel.Voting;

import java.math.BigDecimal;
import java.util.Arrays;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VotingInfoWeb {
    QuestionWeb[] questions;
    BigDecimal amount;
    long timer;

    @NonFinal
    @Setter
    String xmlBody;

    @JsonCreator
    public VotingInfoWeb(@JsonProperty("questions") QuestionWeb[] questions, @JsonProperty("amount") BigDecimal amount, @JsonProperty("timer") long timer, @JsonProperty("xmlBody") String xmlBody) {
        this.questions = questions;
        this.amount = amount;
        this.timer = timer;
        this.xmlBody = xmlBody;
    }

    public VotingInfoWeb(Voting voting, BigDecimal amount, long timer) {
        if (voting == null || voting.getQuestions().length <= 0) {
            questions = new QuestionWeb[0];
        } else {
            questions = Arrays.stream(voting.getQuestions()).map(QuestionWeb::new).toArray(QuestionWeb[]::new);
        }
        this.amount = amount;
        this.timer = timer;
        this.xmlBody = null;
    }
}
